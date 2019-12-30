package com.eparking.informationPush.service.channel.Impl;

import com.eparking.informationPush.entity.biJie.Data;
import com.eparking.informationPush.entity.system.ParkInOut;
import com.eparking.informationPush.entity.system.ParkInOutCache;
import com.eparking.informationPush.entity.system.RouteInfo;
import com.eparking.informationPush.service.channel.BiJieService;
import com.eparking.informationPush.until.BiJieAesTool;
import com.eparking.informationPush.until.Global;
import com.eparking.informationPush.until.HttpUtil;
import com.eparking.informationPush.until.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BiJieServiceImpl implements BiJieService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public void pushData(Integer routeId, ParkInOut parkInOut) {
        logger.info("上传毕节数据"+JsonUtil.beanToJson(parkInOut));
        RouteInfo routeInfo = Global.RouteInfoMap.get(routeId);
        List dataList = new ArrayList();
        Data data = new Data();
        if (StringUtils.isBlank(parkInOut.getOutTime())){
            //进场
            data.setCAR_NO(parkInOut.getInCarPlate());
            data.setCAIJI_DATA_FIRST_TIME(parkInOut.getInTime());
            data.setTIME_1(parkInOut.getInTime());
            data.setVARCHAR50_6(parkInOut.getInCarPlateColor());
            data.setVARCHAR50_11(Global.parkPermissionMap.get(parkInOut.getParkId())+parkInOut.getInPortName());
        }else {
            data.setCAR_NO(parkInOut.getOutCarPlate());
            data.setCAIJI_DATA_FIRST_TIME(parkInOut.getOutTime());
            data.setTIME_10(parkInOut.getOutTime());
            data.setVARCHAR50_6(parkInOut.getOutCarPlateColor());
            data.setVARCHAR50_13(Global.parkPermissionMap.get(parkInOut.getParkId())+parkInOut.getOutPortName());
        }
        dataList.add(data);
        String jsonData = JsonUtil.listToJson(dataList);
        String base64Data = BiJieAesTool.encryptBase64(jsonData, routeInfo.getPassword());
        Map<String,String> pushDataMap = new HashMap<>();
        pushDataMap.put("from",routeInfo.getUsername());
        pushDataMap.put("datatype",Global.BiJieDatatype);
        pushDataMap.put("data",base64Data);
        logger.info("毕节请求报文:"+JsonUtil.beanToJson(pushDataMap));
        String resultMsg = HttpUtil.doPost(routeInfo.getInPath(),pushDataMap);
        logger.info("毕节响应报文:"+resultMsg);
        if (JsonUtil.json2Map(resultMsg).get("code").equals("0")){
            if (StringUtils.isBlank(parkInOut.getOutTime())){
                Global.parkRouteInNumMap.put(parkInOut.getParkId()+"&"+routeId,Global.parkRouteInNumMap.get(parkInOut.getParkId()+"&"+routeId)+1);
            }else {
                Global.parkRouteOutNumMap.put(parkInOut.getParkId()+"&"+routeId,Global.parkRouteOutNumMap.get(parkInOut.getParkId()+"&"+routeId)+1);
            }
        }
    }

    @Override
    public void historyData(Integer routeId, List<ParkInOutCache> parkInOuts) {
        RouteInfo routeInfo = Global.RouteInfoMap.get(routeId);
        List dataList = new ArrayList();
        for (ParkInOutCache parkInOut : parkInOuts) {
            Data data = new Data();
            data.setCAR_NO(parkInOut.getInCarPlate());
            data.setCAIJI_DATA_FIRST_TIME(parkInOut.getInTime());
            data.setTIME_1(parkInOut.getInTime());
            data.setVARCHAR50_6(parkInOut.getInCarPlateColor());
            data.setVARCHAR50_11(Global.parkPermissionMap.get(parkInOut.getParkId()).getParkName()+parkInOut.getInPortName());
            dataList.add(data);
            if (StringUtils.isNotBlank(parkInOut.getOutTime())){
                //有出场
                Data data1 = new Data();
                data1.setCAR_NO(parkInOut.getOutCarPlate());
                data1.setCAIJI_DATA_FIRST_TIME(parkInOut.getOutTime());
                data1.setTIME_10(parkInOut.getOutTime());
                data1.setVARCHAR50_6(parkInOut.getOutCarPlateColor());
                data1.setVARCHAR50_13(Global.parkPermissionMap.get(parkInOut.getParkId()).getParkName()+parkInOut.getOutPortName());
                dataList.add(data1);
            }
        }
        Global.BiJieNum+=dataList.size();
        String jsonData = JsonUtil.listToJson(dataList);
        logger.info("毕节请求数据:"+jsonData);
        String base64Data = BiJieAesTool.encryptBase64(jsonData, routeInfo.getPassword());
        Map<String,String> pushDataMap = new HashMap<>();
        pushDataMap.put("from",routeInfo.getUsername());
        pushDataMap.put("datatype",Global.BiJieDatatype);
        pushDataMap.put("data",base64Data);
        logger.info("毕节请求报文:"+JsonUtil.beanToJson(pushDataMap));
        String resultMsg = HttpUtil.doPost(routeInfo.getInPath(),pushDataMap);
        logger.info("毕节响应报文:"+resultMsg);
    }
}
