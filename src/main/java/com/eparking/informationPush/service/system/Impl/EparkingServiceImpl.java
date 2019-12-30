package com.eparking.informationPush.service.system.Impl;

import com.eparking.informationPush.dao.ParkInOutMapper;
import com.eparking.informationPush.dao.ParkPermissionMapper;
import com.eparking.informationPush.entity.system.ParkInOut;
import com.eparking.informationPush.entity.system.ParkPermission;
import com.eparking.informationPush.service.channel.ParkInOutMainService;
import com.eparking.informationPush.service.system.EparkingService;
import com.eparking.informationPush.until.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EparkingServiceImpl implements EparkingService {
    @Autowired
    private ParkInOutMapper parkInOutMapper;
    @Autowired
    private ParkPermissionMapper parkPermissionMapper;
    @Autowired
    private ParkInOutMainService parkInOutMainService;

    @Override
    public void getEparkingParkInOut(ParkPermission parkPermission) {
        //ParkPermission parkPermission = parkPermissionMapper.selectByPrimaryKey(parkId);
        Map map = new HashMap();
        map.put("parkId",parkPermission.getId().toString());
        map.put("key", MD5Util.doMd5("BDmlp48liikpom9hj7eczwj7aduGh7io"));
        map.put("ID","001");
        Map paramMap = new HashMap();
        paramMap.put("params", JsonUtil.mapToJson(map));
        Map mapResult = JsonUtil.json2Map(HttpUtil.doPost(CommonUtil.eParkingUrl +"/getGovUnUploadRec", paramMap));
        System.out.println(mapResult);
        if (mapResult.get("code").toString().equals("1.0")){
            JSONArray jsonArray = JSONArray.fromObject(mapResult.get("data"));
            List listFullID = new ArrayList();
            for (Object inOut : jsonArray){
                //接受到车易泊云端传过来的流水
                ParkInOut parkInOut = JsonUtil.jsonToBean(inOut.toString(),ParkInOut.class);
                Map mapId = new HashMap();
                mapId.put("id", parkInOut.getId());
                listFullID.add(mapId);
                //查询该车场需要报送的途径有几条
                String[] routes = parkPermission.getRouteIds().split(",");//需要启动加载
                for (String route : routes){
                        parkInOutMainService.parkInOutMainEntrance(Integer.valueOf(route),parkInOut);
                }
            }
            map.put("info","ok");
            map.put("data",JsonUtil.listToJson(listFullID));
            Map paramReturnMap = new HashMap();
            paramReturnMap.put("params",JsonUtil.mapToJson(map));
            //告诉车易泊标识已传输数据
            HttpUtil.doPost(CommonUtil.eParkingUrl +"/setGovUploadedRec", paramReturnMap);
        }
    }

    public static void main(String[] args) {
        EparkingServiceImpl eparkingService = new EparkingServiceImpl();
        //eparkingService.getEparkingParkInOut(381);
    }
}
