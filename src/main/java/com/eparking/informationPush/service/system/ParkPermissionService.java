package com.eparking.informationPush.service.system;

import com.eparking.informationPush.entity.system.ParkPermission;

import java.util.List;

public interface ParkPermissionService {
    /**
     * 查询所有允许上传的车场
     * @return
     */
    List<ParkPermission> getParkPermissionsAllow();

    /**
     * 查询允许上传且指定机构的车场
     * @return
     */
    List<ParkPermission> getParkPermissionsAllowByRoute(Integer routeId);
}
