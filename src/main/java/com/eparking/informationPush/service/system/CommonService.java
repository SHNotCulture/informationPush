package com.eparking.informationPush.service.system;

public interface CommonService {
    /**
     * 日累计进场数
     * @param parkId
     * @return
     */
    Integer totalArrived(Integer parkId);
    /**
     * 日累计离场数
     * @param parkId
     * @return
     */
    Integer totalLeft(Integer parkId);
    /**
     * 当前剩余车位数
     * @param parkId
     * @return
     */
    Integer freeBerth(Integer parkId);
    /**
     * 总车位数
     * @param parkId
     * @return
     */
    Integer totalNum(Integer parkId);
    /**
     * 获取缓存中的剩余车位数，并将当前缓存中的剩余车位数加减1
     * @param parkId
     * @return
     */
    void parkFreeNumAddOrLess(Integer parkId,Boolean isIn);

    void loadGlobal();
    /**
     * 加载开启上传的停车场ID和名称
     */
    void loadParkPermissionMap();

    /**
     * 加载上传渠道基础信息
     */
    void loadRouteInfoMap();

    /**
     * 加载车场id routeID对应渠道的配置信息
     */
    void loadParkRouteConfByParkIdRouteIdMap();

    /**
     * 加载通道信息基础数据
     */
    void loadParkPort();
}
