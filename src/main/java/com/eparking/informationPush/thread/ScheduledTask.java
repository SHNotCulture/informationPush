package com.eparking.informationPush.thread;

import com.eparking.informationPush.dao.ParkInOutCacheMapper;
import com.eparking.informationPush.entity.system.ParkInOut;
import com.eparking.informationPush.entity.system.ParkInOutCache;
import com.eparking.informationPush.entity.system.ParkInOutCacheCriteria;
import com.eparking.informationPush.service.channel.BiJieService;
import com.eparking.informationPush.service.channel.ParkInOutMainService;
import com.eparking.informationPush.service.channel.SHImportExpoService;
import com.eparking.informationPush.service.channel.ShangHaiManagementCommitteeService;
import com.eparking.informationPush.until.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import com.github.pagehelper.autoconfigure.PageHelperProperties;
import com.sun.tracing.dtrace.ArgsAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@EnableScheduling
@EnableAsync
public class ScheduledTask {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired private SHImportExpoService shImportExpoService;
    @Autowired private CallApsService callApsService;
    @Autowired private ShangHaiManagementCommitteeService shangHaiManagementCommitteeService;



    /**
     * 1分钟定时器
     */
    //@Scheduled(fixedRate=1*60*1000)
    @Async
    public void getDtmy() throws Exception {
        /**上海管委会*/
        String m = callApsService.talk("<?xml version=\"1.0\" encoding=\"UTF-8\"?><OSCP Ver=\"1.0\" Osn=\"BIUWAY\"><login><PARKID>\"P005-2\"</PARKID><PLTIME>"+ DateUtil.dataT()+"</PLTIME></login></OSCP>");
        CommonUtil.dtkl = m.substring(m.indexOf("<TOKEN>")+7,m.indexOf("</TOKEN>"));
        shangHaiManagementCommitteeService.pustHeart();
        shangHaiManagementCommitteeService.pustParkInfo();
    }

    /**
     * 2分钟定时器
     */
    //@Scheduled(fixedRate=2*60*1000)
    @Async
    public void shImportExpoSupplement(){
        /**上海进博会补传*/
        shImportExpoService.supplementParkInOut(370,1);
    }

    /**
     * 5分钟定时器
     */
    //@Scheduled(fixedRate=5*60*1000)
    @Async
    public void shImportExpoHeart(){
        /**上海进博会心跳*/
        shImportExpoService.pushHeart(370,1);
    }

    /**
     * 10分钟定时器
     */
    //@Scheduled(fixedRate=10*60*1000)
    @Async
    public void shImportExpoReservation(){
        /**上海进博会预约*/
        shImportExpoService.getReservationInfo();
    }


    @Scheduled(fixedRate=5*1000)
    public void test(){

        /*logger.info("毕节已上传数量:"+Global.BiJieNum+"条");
        ParkInOutCacheCriteria parkInOutCacheCriteria = new ParkInOutCacheCriteria();
        ParkInOutCacheCriteria.Criteria criteria = parkInOutCacheCriteria.createCriteria();
        criteria.andRouteIdEqualTo(2);
        PageHelper.startPage(1,300);//查询起始页
        Page<ParkInOutCache> tOwnerPrepaymentOrders = (Page<ParkInOutCache>)parkInOutCacheMapper.selectByExample(parkInOutCacheCriteria);
        List<ParkInOutCache> parkInOutCacheList = tOwnerPrepaymentOrders.getResult();
        biJieService.historyData(2,parkInOutCacheList);
        List<String> idList = new ArrayList<>();
        for (ParkInOutCache parkInOutCache : parkInOutCacheList){
            idList.add(parkInOutCache.getId());
        }
        ParkInOutCacheCriteria parkInOutCacheCriteriaNew = new ParkInOutCacheCriteria();
        ParkInOutCacheCriteria.Criteria criteriaNew = parkInOutCacheCriteriaNew.createCriteria();
        criteriaNew.andIdIn(idList);
        parkInOutCacheMapper.deleteByExample(parkInOutCacheCriteriaNew);*/
    }


}
