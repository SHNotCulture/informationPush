package com.eparking.informationPush.service.system.Impl;

import com.eparking.informationPush.dao.ParkRouteConfMapper;
import com.eparking.informationPush.entity.system.ParkRouteConf;
import com.eparking.informationPush.entity.system.ParkRouteConfCriteria;
import com.eparking.informationPush.service.system.ParkRouteConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkRouteConfServiceImpl implements ParkRouteConfService {
    @Autowired private ParkRouteConfMapper parkRouteConfMapper;
    @Override
    public ParkRouteConf selectParkRouteConfByPrimaryKey(Integer parkId,Integer routeId) {
        return parkRouteConfMapper.selectByPrimaryKey(parkId,routeId);
    }

    @Override
    public List<ParkRouteConf> selectByParkRouteConf(ParkRouteConf parkRouteConf) {
        return parkRouteConfMapper.selectByExample(getParkRouteConfCriteria(parkRouteConf));
    }

    private ParkRouteConfCriteria getParkRouteConfCriteria(ParkRouteConf parkRouteConf){
        ParkRouteConfCriteria parkRouteConfCriteria = new ParkRouteConfCriteria();
        ParkRouteConfCriteria.Criteria criteria = parkRouteConfCriteria.createCriteria();
        if (parkRouteConf!=null){

        }
        return parkRouteConfCriteria;
    }
}
