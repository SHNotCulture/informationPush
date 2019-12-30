package com.eparking.informationPush.service.channel;

import com.eparking.informationPush.entity.system.ParkInOut;
import com.eparking.informationPush.entity.system.ParkInOutCache;

import java.util.List;

public interface BiJieService {
    void pushData(Integer routeId,ParkInOut parkInOut);

    void historyData(Integer routeId, List<ParkInOutCache> parkInOuts);
}
