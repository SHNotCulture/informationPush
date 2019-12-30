package com.eparking.informationPush.service.system.Impl;

import com.eparking.informationPush.dao.ParkPermissionMapper;
import com.eparking.informationPush.entity.system.ParkPermission;
import com.eparking.informationPush.entity.system.ParkPermissionCriteria;
import com.eparking.informationPush.service.system.ParkPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ParkPermissionServiceImpl implements ParkPermissionService {
    @Autowired
    private ParkPermissionMapper parkPermissionMapper;
    @Override
    public List<ParkPermission> getParkPermissionsAllow() {
        ParkPermissionCriteria parkPermissionCriteria = new ParkPermissionCriteria();
        ParkPermissionCriteria.Criteria criteria = parkPermissionCriteria.createCriteria();
        criteria.andStatusEqualTo(1);
        List<ParkPermission> parkPermissionList = parkPermissionMapper.selectByExample(parkPermissionCriteria);
        return parkPermissionList;
    }

    @Override
    public List<ParkPermission> getParkPermissionsAllowByRoute(Integer routeId) {
        ParkPermissionCriteria parkPermissionCriteria = new ParkPermissionCriteria();
        ParkPermissionCriteria.Criteria criteria = parkPermissionCriteria.createCriteria();
        criteria.andStatusEqualTo(1);
        List<ParkPermission> parkPermissionList = parkPermissionMapper.selectByExample(parkPermissionCriteria);
        for (ParkPermission parkPermission : parkPermissionList){
            String[] route = parkPermission.getRouteIds().split(",");
            if (!Arrays.asList(route).contains(routeId.toString())){
               parkPermissionList.remove(parkPermission);
            }
        }
        return parkPermissionList;
    }

}
