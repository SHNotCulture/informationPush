package com.eparking.informationPush.service.channel;

//上海管委会
public interface ShangHaiManagementCommitteeService {

    /*
    **认证交互
     */
    void authenticationInteraction(Integer parkId);

    /**
     * 心跳
     */
    void pustHeart() throws Exception;

    /**
     * 发送停车数
     */
    void pustParkInfo() throws Exception;

}
