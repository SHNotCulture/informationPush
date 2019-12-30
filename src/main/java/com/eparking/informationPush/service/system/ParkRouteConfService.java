package com.eparking.informationPush.service.system;

import com.eparking.informationPush.entity.system.ParkRouteConf;

import java.util.List;

public interface ParkRouteConfService {

    ParkRouteConf selectParkRouteConfByPrimaryKey(Integer parkId,Integer routeId);

    List<ParkRouteConf> selectByParkRouteConf(ParkRouteConf parkRouteConf);
}
