package com.eparking.informationPush.service.channel.Impl;

import com.eparking.informationPush.service.channel.ShangHaiManagementCommitteeService;
import com.eparking.informationPush.until.CallApsService;
import com.eparking.informationPush.until.CommonUtil;
import com.eparking.informationPush.until.DateUtil;
import com.eparking.informationPush.until.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShangHaiManagementCommitteeServiceImpl implements ShangHaiManagementCommitteeService {

    @Autowired
    private CallApsService callApsService;

    @Override
    public void authenticationInteraction(Integer parkId) {

    }

    @Override
    public void pustHeart() throws Exception {
        String msg = callApsService.talk("<?xml version=\"1.0\" encoding=\"UTF-8\"?><OSCP Ver=\"1.0\" Osn=\"BIUWAY\" TOKEN=\""+ CommonUtil.dtkl +"\"><HB>"+ DateUtil.dataT() +"</HB></OSCP>");
    }

    @Override
    public void pustParkInfo() throws Exception {
        String totalmsg = HttpClientUtil.doGet("http://exchange.eparking.top:8080/GZBZTheThirdAPI/dataVAction?service=totalParkingSpace&parkId=370");
        Integer spacereNum = Integer.valueOf(HttpClientUtil.doGet("http://exchange.eparking.top:8080/GZBZTheThirdAPI/dataVAction?service=totalParkingSpaceFree&parkId=370"));
        Integer reservationNum = CommonUtil.reservationNum;
        System.out.println("总车位："+totalmsg+",剩余车位（未除预约车）："+spacereNum+",预约车位："+reservationNum);
        spacereNum -= reservationNum;
        callApsService.talk("<?xml version=\"1.0\" encoding=\"UTF-8\"?><OSCP Ver=\"1.0\" Osn=\"BIUWAY\" TOKEN=\""+ CommonUtil.dtkl +"\"><OTDATA>" +
                "<RTPARK DevID=\"P005-2\"><PLTIME>"+DateUtil.dataT()+"</PLTIME><PARKSTATE>0</PARKSTATE><SPACENUM>"+totalmsg+"</SPACENUM><SPACEREMAINNUM>"+spacereNum+"</SPACEREMAINNUM><GUID>"+CommonUtil.dtkl+"</GUID></RTPARK></OTDATA></OSCP>");
    }

}
