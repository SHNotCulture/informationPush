package com.eparking.informationPush.dao.sqlProvider;


public class CustomizeMapperSqlProvider{

    public String  updateParkingReservationNum(Integer parkId,String reservationNum){
        String sql ="update company_park set parking_id = '"+reservationNum+"' where id = "+parkId+"";
        return sql.toString();
    }
}
