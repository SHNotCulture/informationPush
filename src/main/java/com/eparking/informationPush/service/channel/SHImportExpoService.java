package com.eparking.informationPush.service.channel;

import com.eparking.informationPush.entity.system.ParkInOut;

//上海进博会
public interface SHImportExpoService {
    /**
     * 进博会主入口
     * @param routeId
     * @param parkInOut
     */
    void pushMainEnter(Integer routeId, ParkInOut parkInOut);
    /**
     * 进场推送
     * @param parkInOut
     * @param route
     */
    void pushParkIn(ParkInOut parkInOut,Integer route);
    /**
     * 出场推送
     * @param parkInOut
     * @param route
     */
    void pushParkOut(ParkInOut parkInOut,Integer route);
    /**
     * 心跳
     * @param parkId
     * @param route
     */
    void pushHeart(Integer parkId,Integer route);
    /**
     * 补传
     * @param parkId
     * @param route
     */
    void supplementParkInOut(Integer parkId,Integer route);
    /**
     * 获取预约信息
     */
    void getReservationInfo();
}
