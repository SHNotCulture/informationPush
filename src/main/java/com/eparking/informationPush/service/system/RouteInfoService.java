package com.eparking.informationPush.service.system;

import com.eparking.informationPush.entity.system.RouteInfo;

import java.util.List;

public interface RouteInfoService {
    RouteInfo selectRouteInfoByPrimaryKey(Integer id);
    List<RouteInfo> getRouteInfoList(RouteInfo routeInfo);
}
