package com.eparking.informationPush.service.channel;

import com.eparking.informationPush.entity.system.ParkInOut;


public interface ParkInOutMainService {
    /**
     * 进出场数据总入口
     * @param route
     * @param parkInOut
     */
    void parkInOutMainEntrance(Integer route,ParkInOut parkInOut);


    //void heartMainEntrance(Integer parkId,Integer route);

}
