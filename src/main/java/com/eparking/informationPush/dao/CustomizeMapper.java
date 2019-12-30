package com.eparking.informationPush.dao;

import com.eparking.informationPush.dao.sqlProvider.CustomizeMapperSqlProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface CustomizeMapper {

    @UpdateProvider(type= CustomizeMapperSqlProvider.class, method="updateParkingReservationNum")
    int updateParkingReservationNum(Integer parkId,String reservationNum);
}
