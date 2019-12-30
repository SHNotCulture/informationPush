package com.eparking.informationPush.thread;

import com.eparking.informationPush.entity.system.ParkInOut;
import com.eparking.informationPush.entity.system.ParkRouteConf;
import com.eparking.informationPush.service.channel.ParkInOutMainService;
import com.eparking.informationPush.service.system.CommonService;
import com.eparking.informationPush.until.*;
import com.eparking.informationPush.until.MQTT.MQTTTaskData;
import com.eparking.informationPush.until.MQTT.MqttPushClient;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Async
@Component
public class MqttThread {
    private static final Logger logger = LoggerFactory.getLogger(MqttThread.class);
    @Autowired
    private ParkInOutMainService parkInOutMainService;
    @Autowired
    private CommonService commonService;

    //private String host="tcp://yun1.eparking.top:1883";
    private String host="tcp://exchange.eparking.top:1883";
    private String  clientID="eparkingPushClient1";
    private String  username="eparking";
    private String  password="yxQZDbpeGuZT1Hzr";
    private Integer timeout=10;
    private Integer keepalive=20;
    /**
     * 间隔5S触发
     */
    @Scheduled(cron = "*/5 * * * * ? ")
    public void checkMqtt(){
        if(MqttPushClient.getClient()==null)
        {
            logger.info("本地MQTT第一次连接...");
            //logger.info(mqttConfiguration.getClientid());
            MqttPushClient.connect(host,clientID,username,password,timeout,keepalive);
            MqttPushClient.subscribe("eparkcloud/epark_online_system/#",2);
        }
        if(!MqttPushClient.connected()){
            logger.info("本地MQTT尝试连接...");
            MqttPushClient.connect(host,clientID,username,password,timeout,keepalive);
            MqttPushClient.subscribe("eparkcloud/epark_online_system/#",2);
        }else{
            //logger.info("本地MQTT连接正常");
        }

    }
    @Async
    public  void saveMqttResult(String datastr,String topic) {
        try{
            logger.info("接收到MQTT回传数据:"+topic);
            if(datastr.length()>8){
                byte[] firstData= StringUtil.decryptBASE64(datastr);//base64解密
                //System.out.println("firstData:"+firstData);
                byte[] secondData= SecretUtils.decryptMode(firstData);//3des解密
                String thirdData=new String(secondData);
                //logger.info("thirdData:"+thirdData);
                MQTTTaskData mqttTaskData= JsonUtil.jsonToBean(thirdData,MQTTTaskData.class);
                //logger.info(mqttTaskData.toString());
                        switch (mqttTaskData.getTask_type()){
                            case "park_in_out":
                                //System.out.println(mqttTaskData.getData());
                                //logger.info("MQTT接受parkinout");
                                Map map = JsonUtil.json2Map(mqttTaskData.getData());
                                if (StringUtils.isBlank(map.get("coupon").toString())) {
                                    map.put("coupon", 0);
                                }
                                if (StringUtils.isBlank(map.get("ePayType").toString())) {
                                    map.remove("ePayType");
                                }
                                ParkInOut parkInOut = JsonUtil.jsonToBean(JsonUtil.mapToJson(map), ParkInOut.class);
                                parkInOut.setInCarPlate(StringUtil.deCode(parkInOut.getInCarPlate()));
                                parkInOut.setInCarPlateColor(StringUtil.deCode(parkInOut.getInCarPlateColor()));
                                parkInOut.setInPortName(StringUtil.deCode(parkInOut.getInPortName()));
                                parkInOut.setOutCarPlate(StringUtil.deCode(parkInOut.getOutCarPlate()));
                                parkInOut.setOutCarPlateColor(StringUtil.deCode(parkInOut.getOutCarPlateColor()));
                                parkInOut.setOutPortName(StringUtil.deCode(parkInOut.getOutPortName()));
                                /*if (Global.parkPermissionMap.containsKey(parkInOut.getParkId())) {
                                    logger.info("收到需上传的数据"+JsonUtil.beanToJson(parkInOut));
                                    //logger.info("MQTT数据："+JsonUtil.beanToJson(parkInOut));
                                    parkInOutMainService = SpringUtils.getBean(ParkInOutMainService.class);
                                    commonService = SpringUtils.getBean(CommonService.class);
                                    //查询某车场同时有几个推送渠道
                                    Integer[] routeIds = StringUtil.commaString(Global.parkPermissionMap.get(parkInOut.getParkId()).getRouteIds());
                                    //修改缓存中的停车数量
                                    commonService.parkFreeNumAddOrLess(parkInOut.getParkId(),StringUtils.isBlank(parkInOut.getOutTime()));
                                    for (Integer routeId : routeIds){
                                        parkInOutMainService.parkInOutMainEntrance(routeId, parkInOut);
                                    }
                                }*/
                                break;
                        }
            }
        }
        catch (Exception e){
            logger.info("Mqtt数据接收错误,错误原因"+e.toString());
        }
    }


}
