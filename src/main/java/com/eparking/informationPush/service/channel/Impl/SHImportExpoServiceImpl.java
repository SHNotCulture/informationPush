package com.eparking.informationPush.service.channel.Impl;
import com.eparking.informationPush.dao.ParkInOutCacheMapper;
import com.eparking.informationPush.dao.RouteInfoMapper;
import com.eparking.informationPush.entity.shImportExpo.Heart;
import com.eparking.informationPush.entity.shImportExpo.ParkIn;
import com.eparking.informationPush.entity.shImportExpo.ParkOut;
import com.eparking.informationPush.entity.system.*;
import com.eparking.informationPush.service.system.CommonService;
import com.eparking.informationPush.service.system.ParkPermissionService;
import com.eparking.informationPush.service.channel.SHImportExpoService;
import com.eparking.informationPush.service.system.ParkRouteConfService;
import com.eparking.informationPush.service.system.RouteInfoService;
import com.eparking.informationPush.until.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SHImportExpoServiceImpl implements SHImportExpoService {
    private  static final Logger logger= LoggerFactory.getLogger(SHImportExpoServiceImpl.class);

    @Autowired private RouteInfoMapper routeInfoMapper;
    @Autowired private ParkInOutCacheMapper parkInOutCacheMapper;
    @Autowired private ParkPermissionService parkPermissionService;
    @Autowired private ParkRouteConfService parkRouteConfService;
    @Autowired private RouteInfoService routeInfoService;
    @Autowired private CommonService commonService;

    @Override
    public void pushMainEnter(Integer routeId, ParkInOut parkInOut) {
        try {
            ParkInOutCache parkInOutCache = new ParkInOutCache();
            Integer dataDiff;
            if (StringUtils.isBlank(parkInOut.getOutTime())){
                //进场
                dataDiff = DateUtil.getInterval(parkInOut.getInTime(),DateUtil.getCurDateTime(),0);
                if (dataDiff<=30){
                    pushParkIn(parkInOut,routeId);
                }else {
                    //延迟大于30秒，先保存，走补传接口
                    parkInOutCache = (ParkInOutCache)BeanCopyUtil.CopyBeanToBean(parkInOut,parkInOutCache);
                    parkInOutCacheMapper.insertSelective(parkInOutCache);
                }
            }else {
                //出场
                dataDiff = DateUtil.getInterval(parkInOut.getOutTime(),DateUtil.getCurDateTime(),0);
                if (dataDiff<=30){
                    pushParkOut(parkInOut,routeId);
                }else {
                    parkInOutCache = (ParkInOutCache)BeanCopyUtil.CopyBeanToBean(parkInOut,parkInOutCache);
                    if (parkInOutCacheMapper.selectByPrimaryKey(parkInOut.getId())==null){
                        parkInOutCacheMapper.insertSelective(parkInOutCache);
                    }else {
                        //存在对应的进场记录也在缓存表里，则先删除进场的数据，再保存离场的完整数据
                        parkInOutCacheMapper.deleteByPrimaryKey(parkInOut.getId());
                        parkInOutCacheMapper.insertSelective(parkInOutCache);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void pushParkIn(ParkInOut parkInOut, Integer route) {
        Integer spaceNum = Global.parkFreeNumMap.get(parkInOut.getParkId());
        ParkRouteConf parkRouteConf = parkRouteConfService.selectParkRouteConfByPrimaryKey(parkInOut.getParkId(), route);
        RouteInfo routeInfo = routeInfoService.selectRouteInfoByPrimaryKey(route);
        ParkIn parkIn = packageParkIn(parkInOut, spaceNum, routeInfo.getPassword());
        String url = getUrl("/data/parkplot/arrive", routeInfo, parkRouteConf);
        logger.info("进博会进场报文:" + JsonUtil.beanToJson(parkIn));
        String result = HttpClientUtil.doPostJson(url, JsonUtil.beanToJson(parkIn));
        logger.info("进博会进场：" + result);
    }

    @Override
    public void pushParkOut(ParkInOut parkInOut,Integer route) {
        Integer spaceNum = Global.parkFreeNumMap.get(parkInOut.getParkId());
        ParkRouteConf parkRouteConf = parkRouteConfService.selectParkRouteConfByPrimaryKey(parkInOut.getParkId(),route);
        RouteInfo routeInfo = routeInfoMapper.selectByPrimaryKey(route);
            ParkOut parkOut = packageParkOut(parkInOut,spaceNum,routeInfo.getPassword());
            String url = getUrl("/data/parkplot/leave",routeInfo,parkRouteConf);
            logger.info("进博会出场报文："+JsonUtil.beanToJson(parkOut));
            String result = HttpClientUtil.doPostJson(url,JsonUtil.beanToJson(parkOut));
            logger.info("进博会出场："+result);
    }

    @Override
    public void pushHeart(Integer parkId,Integer route) {
            ParkRouteConf parkRouteConf = parkRouteConfService.selectParkRouteConfByPrimaryKey(parkId,route);
            RouteInfo routeInfo = routeInfoMapper.selectByPrimaryKey(route);
            String totalArrived  = HttpClientUtil.doGet("http://exchange.eparking.top:8080/GZBZTheThirdAPI/dataVAction?service=entranceFlow&parkId="+parkId+"");
            String totalLeft  = HttpClientUtil.doGet("http://exchange.eparking.top:8080/GZBZTheThirdAPI/dataVAction?service=outboundFlow&parkId="+parkId+"");
            Heart heart = new Heart();
            heart.setTotalArrived(Integer.valueOf(totalArrived));
            heart.setTotalLeft(Integer.valueOf(totalLeft));
            heart.setFreeBerth(Global.parkFreeNumMap.get(parkId));
            heart.setDataTime(Long.valueOf(DateUtil.getDateMillisecond(DateUtil.getCurrDateTime(),DateUtil.sdfDateTimeFormat)));
            Map<String,String> map = new HashMap<>();
            map.put("totalArrived",heart.getTotalArrived().toString());
            map.put("totalLeft",heart.getTotalLeft().toString());
            map.put("freeBerth",heart.getFreeBerth().toString());
            map.put("dataTime",heart.getDataTime().toString());
            String sign = Encrypt.getSignJinBoHui(map,routeInfo.getPassword());//启动加载
            heart.setSign(sign);
            String url = getUrl("/manage/parkplot/heartbeat",routeInfo,parkRouteConf);
            logger.info("进博会心跳报文："+JsonUtil.beanToJson(heart));
            String result = HttpClientUtil.doPostJson(url,JsonUtil.beanToJson(heart));
            logger.info("进博会心跳："+result);
    }

    @Override
    public void supplementParkInOut(Integer parkId, Integer route) {
        ParkRouteConf parkRouteConf = parkRouteConfService.selectParkRouteConfByPrimaryKey(parkId,route);
        RouteInfo routeInfo = routeInfoMapper.selectByPrimaryKey(route);
        ParkInOutCacheCriteria parkInOutCacheCriteria = new ParkInOutCacheCriteria();
        ParkInOutCacheCriteria.Criteria criteria = parkInOutCacheCriteria.createCriteria();
        criteria.andParkIdEqualTo(parkId);
        List<ParkInOutCache> parkInOutCacheList =  parkInOutCacheMapper.selectByExample(parkInOutCacheCriteria);
        List<ParkIn> parkInList = new ArrayList<>();
        List<ParkOut> parkOutList = new ArrayList<>();
        boolean haveParkInOutCache = parkInOutCacheList.size()>0;
        if (haveParkInOutCache){
            Integer spaceNum = Global.parkFreeNumMap.get(parkId);
            for (ParkInOutCache parkInOutCache : parkInOutCacheList){
                ParkInOut parkInOut = new ParkInOut();
                try {
                    parkInOut = (ParkInOut)BeanCopyUtil.CopyBeanToBean(parkInOutCache,parkInOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ParkIn parkIn = packageParkIn(parkInOut, spaceNum, routeInfo.getPassword());
                parkInList.add(parkIn);
                if (!StringUtils.isBlank(parkInOut.getOutTime())){
                    ParkOut parkOut = packageParkOut(parkInOut, spaceNum, routeInfo.getPassword());
                    parkOutList.add(parkOut);
                }
                parkInOutCacheMapper.deleteByPrimaryKey(parkInOutCache.getId());
            }
            Map map = new HashMap();
            JSONArray jsonArrayIn = JSONArray.fromObject(JsonUtil.listToJson(parkInList));
            JSONArray jsonArrayOut = JSONArray.fromObject(JsonUtil.listToJson(parkOutList));
            map.put("arrive", jsonArrayIn);
            map.put("leave", jsonArrayOut);
            String url = getUrl("/data/resend", routeInfo, parkRouteConf);
            logger.info("进博会补传数据："+JsonUtil.mapToJson(map));
            String result = HttpClientUtil.doPostJson(url, JsonUtil.mapToJson(map));
            logger.info("进博会补传：" + result);
        }
    }

    @Override
    public void getReservationInfo() {
        List<ParkPermission> permissionList = parkPermissionService.getParkPermissionsAllowByRoute(1);
        RouteInfo routeInfo = routeInfoMapper.selectByPrimaryKey(1);
        for (ParkPermission parkPermission : permissionList){
            ParkRouteConf parkRouteConf = parkRouteConfService.selectParkRouteConfByPrimaryKey(parkPermission.getId(),1);
            String url = getUrl("/query/reserved",routeInfo,parkRouteConf);
            String result = HttpClientUtil.doPost(url);
            Map map = JsonUtil.json2Map(result);
            double code = Double.valueOf(map.get("code").toString());
            //logger.info("进博会预约信息：" + result);
            if ((int)code==0){
                JSONArray jsonArrayOld = JSONArray.fromObject(JSONObject.fromObject(map.get("data")).get("reservations").toString());
                JSONArray jsonArray = getMinimumJSONArray(jsonArrayOld);
                logger.info("获取预约信息："+jsonArrayOld.size()+"条");
                logger.info("当前下发："+jsonArray.size()+"条");
                //customizeMapper.updateParkingReservationNum(370,jsonArray.size()+"");
                CommonUtil.reservationNum = jsonArray.size();
                Map mapMsg = new HashMap();
                mapMsg.put("flag",3);
                mapMsg.put("parkId", parkPermission.getId());
                JSONArray jsonArrayTask = new JSONArray();
                for (Object obj : jsonArray) {
                    JSONObject reservation = JSONObject.fromObject(obj);
                    Map mapTask = new HashMap();
                    mapTask.put("carPlate", StringUtil.enCode(reservation.get("plateId").toString()));
                    mapTask.put("carType", reservation.get("vehicleType"));
                    Integer reservedType = (int) (double) Double.valueOf(reservation.get("reservedType").toString());
                    String day = reservation.get("reservedDay").toString();
                    String beginTime = null;
                    String endTime = null;
                    switch (reservedType) {
                        case 1:
                            beginTime = day + " 08:00:00";
                            endTime = day + " 13:00:00";
                            break;
                        case 2:
                            beginTime = day + " 13:00:00";
                            endTime = day + " 18:00:00";
                            break;
                        case 3:
                            beginTime = day + " 08:00:00";
                            endTime = day + " 18:00:00";
                            break;
                        default:
                            break;
                    }
                    mapTask.put("beginTime", beginTime);
                    mapTask.put("endTime", endTime);
                    jsonArrayTask.add(mapTask);
                }
                mapMsg.put("data", jsonArrayTask);
                HttpClientUtil.doPostJson("http://yun2.eparking.top:8086/mqttClient/inside/reservedCar", JsonUtil.mapToJson(mapMsg));
            }
        }
    }

    /**
     * 根据车牌颜色返回车型
     * @param plateColor
     * @return
     */
    private Integer getVehicleType(String plateColor){
        Integer vehicleType = 9;
        if (!StringUtils.isBlank(plateColor)){
            if (plateColor.substring(0,1).equals("蓝")){
                vehicleType = 3;
            }if (plateColor.substring(0,1).equals("黄")){
                vehicleType = 1;
            }
        }
        return vehicleType;
    }



    private Integer getParkType(Integer carNature){
        Integer parkType = 1;
        if (carNature==2){
            parkType = 2;
        }
        return parkType;
    }

    private String getPayType(ParkInOut parkInOut){
        String payType = "unknown";
        if (parkInOut.getActualPay()!=0){
            payType="cash";
        }if (parkInOut.getePayType()!=0){
            switch (parkInOut.getePayType()){
                case 2:
                    payType="uppay";
                    break;
                case 3:
                    payType="wechat";
                    break;
                case 4:
                    payType="alipay";
                    break;
                default:
                    break;
            }
        }
        return payType;
    }

    private String getUrl(String path,RouteInfo routeInfo,ParkRouteConf parkRouteConf){
        String nonce = UUID.randomUUID().toString();
        String curTime = DateUtil.getDateMillisecond(DateUtil.getCurrDateTime(),DateUtil.sdfDateTimeFormat);
        String checkSum = CheckSumBuilder.getCheckSum(routeInfo.getPassword(),nonce,curTime);
        String url = "http://"+routeInfo.getServerIp()+":"+routeInfo.getServerPort()+"/service/parking"+path+"/"+parkRouteConf.getRouteParkId()+"?appId="+routeInfo.getUsername()+"&nonce="+nonce+"&curTime="+curTime+"&checkSum="+checkSum+"";
        return url;
    }

    private Integer getLaneType(Integer outType){
        Integer lanType = 2;
        if (outType==9 || outType==12){
            lanType = 1;
        }
        return lanType;
    }

    private ParkIn packageParkIn(ParkInOut parkInOut,Integer spaceNum,String password){
        ParkIn parkIn = new ParkIn();
        parkIn.setSeq(parkInOut.getId());
        parkIn.setPlateId(parkInOut.getInCarPlate());
        parkIn.setVehicleType(getVehicleType(parkInOut.getInCarPlateColor()));
        parkIn.setLaneType(2);
        parkIn.setFreeBerth(spaceNum);
        parkIn.setParkType(getParkType(parkInOut.getCarNature()));
        parkIn.setDataTime(DateUtil.getDateMillisecond(parkInOut.getInTime(),DateUtil.sdfDateTimeFormat));
        Map mapSign = new HashMap();
        mapSign.put("plateId",parkIn.getPlateId());
        mapSign.put("vehicleType",parkIn.getVehicleType().toString());
        mapSign.put("freeBerth",parkIn.getFreeBerth().toString());
        mapSign.put("dataTime",parkIn.getDataTime());
        String sign = Encrypt.getSignJinBoHui(mapSign, password);
        parkIn.setSign(sign);
        return parkIn;
    }

    private ParkOut packageParkOut(ParkInOut parkInOut,Integer spacereNum,String password){
        ParkOut parkOut = new ParkOut();
        parkOut.setSeq(parkInOut.getId());
        parkOut.setPlateId(parkInOut.getOutCarPlate());
        parkOut.setVehicleType(getVehicleType(parkInOut.getOutCarPlateColor()));
        parkOut.setLaneType(getLaneType(parkInOut.getOutType()));
        parkOut.setParkingTime(DateUtil.getInterval(parkInOut.getInTime(),parkInOut.getOutTime(),0));
        parkOut.setParkType(getParkType(parkInOut.getCarNature()));
        parkOut.setFreeBerth(spacereNum);
        parkOut.setPayMoney(Integer.valueOf(MoneyUtil.changeY2F(parkInOut.getNeedPay().toString())));
        parkOut.setPayType(getPayType(parkInOut));
        parkOut.setDataTime(DateUtil.getDateMillisecond(parkInOut.getOutTime(),DateUtil.sdfDateTimeFormat));
        Map<String,String> mapSign = new HashMap();
        mapSign.put("plateId",parkOut.getPlateId());
        mapSign.put("vehicleType",parkOut.getVehicleType().toString());
        mapSign.put("freeBerth",parkOut.getFreeBerth().toString());
        mapSign.put("laneType",parkOut.getLaneType().toString());
        mapSign.put("dataTime",parkOut.getDataTime());
        mapSign.put("parkingTime",parkOut.getParkingTime().toString());
        mapSign.put("payMoney",parkOut.getPayMoney().toString());
        String sign = Encrypt.getSignJinBoHui(mapSign,password);
        parkOut.setSign(sign);
        return parkOut;
    }

    public JSONArray getMinimumJSONArray(JSONArray jsonArray){
        Map<String,Object> map = new HashMap();
        for (Object obj : jsonArray){
            String carNo = JsonUtil.json2Map(obj.toString()).get("plateId").toString();
            JSONObject jsonObjectNew = JSONObject.fromObject(obj);
            Long timeNew = Long.valueOf(DateUtil.getDateMillisecond(jsonObjectNew.get("reservedDay").toString(),DateUtil.g_SimpleDateFormat_I));
            Long timeNow = Long.valueOf(DateUtil.getDateMillisecond(DateUtil.getCurDateTime(),DateUtil.g_SimpleDateFormat_I));
            if (map.containsKey(carNo)){
                JSONObject jsonObjectOld = (JSONObject)map.get(carNo);
                Long timeOld = Long.valueOf(DateUtil.getDateMillisecond(jsonObjectOld.get("reservedDay").toString(),DateUtil.g_SimpleDateFormat_I));
                if (timeNew<timeOld){
                    map.put(carNo,obj);
                }
            }else {
                if (timeNew>=timeNow){
                    map.put(carNo,obj);
                }
            }
        }
        JSONArray jsonArrayNew = new JSONArray();
        for (String carPlate: map.keySet()){
            jsonArrayNew.add(map.get(carPlate));
        }
        return jsonArrayNew;
    }

    public static void main(String[] args) {
        Long timeNew = Long.valueOf(DateUtil.getDateMillisecond("2019-11-08",DateUtil.g_SimpleDateFormat_I));
        Long timeNow = Long.valueOf(DateUtil.getDateMillisecond(DateUtil.getCurDateTime(),DateUtil.g_SimpleDateFormat_I));
        System.out.println(timeNow);
        System.out.println(timeNew);
        System.out.println(timeNew>=timeNow);
    }
}
