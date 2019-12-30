package com.eparking.informationPush.service.channel.Impl;
import com.eparking.informationPush.entity.system.ParkInOut;
import com.eparking.informationPush.service.channel.BiJieService;
import com.eparking.informationPush.service.channel.ParkInOutMainService;
import com.eparking.informationPush.service.channel.SHImportExpoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkInOutMainServiceImpl implements ParkInOutMainService {
    @Autowired
    private SHImportExpoService shImportExpoService;
    @Autowired
    private BiJieService bijieService;


    @Override
    public void parkInOutMainEntrance(Integer routeId, ParkInOut parkInOut) {
        switch (routeId){
            case 1:
                //上海进博会
                //shImportExpoService.pushMainEnter(routeId,parkInOut);
                break;
            case 2:
                //毕节
                bijieService.pushData(routeId,parkInOut);
                break;
            default:
                break;
        }
    }

    /*@Override
    public void heartMainEntrance(Integer parkId, Integer route) {
        switch (route){
            case 1:
                //shImportExpoService.pushHeart(parkId);
                break;
            default:
                break;
        }
    }*/
}
