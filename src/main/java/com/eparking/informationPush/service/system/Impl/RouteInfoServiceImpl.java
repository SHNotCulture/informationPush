package com.eparking.informationPush.service.system.Impl;

import com.eparking.informationPush.dao.RouteInfoMapper;
import com.eparking.informationPush.entity.system.RouteInfo;
import com.eparking.informationPush.entity.system.RouteInfoCriteria;
import com.eparking.informationPush.service.system.RouteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteInfoServiceImpl implements RouteInfoService {
    @Autowired private RouteInfoMapper routeInfoMapper;

    @Override
    public RouteInfo selectRouteInfoByPrimaryKey(Integer id) {
        return routeInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RouteInfo> getRouteInfoList(RouteInfo routeInfo) {
        return routeInfoMapper.selectByExample(getRouteInfoCriteria(routeInfo));
    }

    private RouteInfoCriteria getRouteInfoCriteria(RouteInfo routeInfo){
        RouteInfoCriteria routeInfoCriteria = new RouteInfoCriteria();
        RouteInfoCriteria.Criteria criteria = routeInfoCriteria.createCriteria();
        if (routeInfo!=null){
        }
        return routeInfoCriteria;
    }
}
