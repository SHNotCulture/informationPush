package com.eparking.informationPush.service.system.Impl;

import com.eparking.informationPush.entity.system.ParkPermission;
import com.eparking.informationPush.entity.system.ParkRouteConf;
import com.eparking.informationPush.entity.system.RouteInfo;
import com.eparking.informationPush.service.system.CommonService;
import com.eparking.informationPush.service.system.ParkPermissionService;
import com.eparking.informationPush.service.system.ParkRouteConfService;
import com.eparking.informationPush.service.system.RouteInfoService;
import com.eparking.informationPush.until.Global;
import com.eparking.informationPush.until.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired private ParkPermissionService parkPermissionService;
    @Autowired private RouteInfoService routeInfoService;
    @Autowired private ParkRouteConfService parkRouteConfService;

    @Override
    public Integer totalArrived(Integer parkId) {
        return Integer.valueOf(HttpClientUtil.doGet("http://exchange.eparking.top:8080/GZBZTheThirdAPI/dataVAction?service=entranceFlow&parkId="+parkId+""));
    }

    @Override
    public Integer totalLeft(Integer parkId) {
        return Integer.valueOf(HttpClientUtil.doGet("http://exchange.eparking.top:8080/GZBZTheThirdAPI/dataVAction?service=outboundFlow&parkId="+parkId+""));
    }

    @Override
    public Integer freeBerth(Integer parkId) {
        return Integer.valueOf(HttpClientUtil.doGet("http://exchange.eparking.top:8080/GZBZTheThirdAPI/dataVAction?service=totalParkingSpaceFree&parkId="+parkId+""));
    }

    @Override
    public Integer totalNum(Integer parkId) {
        return Integer.valueOf(HttpClientUtil.doGet("http://exchange.eparking.top:8080/GZBZTheThirdAPI/dataVAction?service=totalParkingSpace&parkId="+parkId+""));
    }

    @Override
    public void parkFreeNumAddOrLess(Integer parkId, Boolean isIn) {
        //当前缓存中的剩余车位数
        Integer spaceNum = Global.parkFreeNumMap.get(parkId);
        if (isIn){
            //进场-1
            Global.parkFreeNumMap.put(parkId,spaceNum-1);
        }else {
            Global.parkFreeNumMap.put(parkId,spaceNum+1);
        }
    }

    @Override
    public void loadGlobal() {
        loadParkPermissionMap();
        loadRouteInfoMap();
        loadParkRouteConfByParkIdRouteIdMap();
    }


    @Override
    public void loadParkPermissionMap(){
        List<ParkPermission> parkPermissions = parkPermissionService.getParkPermissionsAllow();
        for (ParkPermission parkPermission : parkPermissions){
            Global.parkPermissionMap.put(parkPermission.getId(),parkPermission);
        }
    }

    @Override
    public void loadRouteInfoMap() {
        RouteInfo routeInfo = new RouteInfo();
        List<RouteInfo> routeInfoList = routeInfoService.getRouteInfoList(routeInfo);
        for (RouteInfo routeInfoNew : routeInfoList){
            Global.RouteInfoMap.put(routeInfoNew.getId(),routeInfoNew);
        }
    }

    @Override
    public void loadParkRouteConfByParkIdRouteIdMap() {
        ParkRouteConf parkRouteConf = new ParkRouteConf();
        List<ParkRouteConf> parkRouteConfList = parkRouteConfService.selectByParkRouteConf(parkRouteConf);
        for (ParkRouteConf parkRouteConfNew : parkRouteConfList){
            Global.parkRouteConfByParkIdRouteIdMap.put(parkRouteConfNew.getParkId()+"&"+parkRouteConfNew.getRouteId(),parkRouteConfNew);
        }
    }

    @Override
    public void loadParkPort() {

    }

}
