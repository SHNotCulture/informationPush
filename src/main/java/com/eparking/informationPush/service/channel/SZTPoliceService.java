package com.eparking.informationPush.service.channel;

import com.eparking.informationPush.entity.system.ParkInOut;

//深圳交警
public interface SZTPoliceService {
    /**
     * 上传进场
     * @param parkInOut
     * @param routeId
     */
    void pushParkIn(ParkInOut parkInOut, Integer routeId);
    /**
     * 上传出场
     * @param parkInOut
     * @param routeId
     */
    void pushParkOut(ParkInOut parkInOut,Integer routeId);


}
