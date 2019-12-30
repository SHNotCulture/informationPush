package com.eparking.informationPush.until;

import com.eparking.informationPush.entity.system.ParkPermission;
import com.eparking.informationPush.entity.system.ParkRouteConf;
import com.eparking.informationPush.entity.system.RouteInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Global {

    /**
     * 车易泊基础数据
     */
    /**车场剩余车位数 */
    public static Map<Integer,Integer> parkFreeNumMap = new HashMap<Integer,Integer>();
    /**车场总车位数 */
    public static Map<Integer,Integer> parkTotalNumMap = new HashMap<Integer,Integer>();



    /**
     * 系统基础数据
     */
    /**允许上传的车场表*/
    public static Map<Integer, ParkPermission> parkPermissionMap = new HashMap<Integer, ParkPermission>();
    /**渠道配置信息*/
    public static Map<Integer, RouteInfo> RouteInfoMap = new HashMap<Integer, RouteInfo>();
    /**根据parkId和routeId获取routeConf(key格式：parkId&routeId)*/
    public static Map<String, ParkRouteConf> parkRouteConfByParkIdRouteIdMap = new HashMap<String, ParkRouteConf>();
    /**当前小时车场渠道上传成功的进场数量(key格式：parkId&routeId)*/
    public static Map<String,Integer> parkRouteInNumMap = new HashMap<String,Integer>();
    /**当前小时车场渠道上传成功的出场数量(key格式：parkId&routeId)*/
    public static Map<String,Integer> parkRouteOutNumMap = new HashMap<String,Integer>();




    /**
     * 华为云obs
     */
    public static String ak = "KCF2TXSIO353IVRGKAA4";
    public static String sk = "GonyHF4GBVSUh5Grs6tT7bQNz17T3uNgqaVKRXfe";
    public static String endPoint = "obs.cn-south-1.myhwclouds.com";


    /**
     * 毕节相关参数
     */
    public static String BiJieDatatype = "TCXX";
    public static Integer BiJieNum = 0;



}
