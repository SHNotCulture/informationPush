package com.eparking.informationPush.entity.shImportExpo;

public class ParkIn {
    private String seq;
    private String plateId;
    private Integer vehicleType;
    private Integer laneType;
    private Integer freeBerth;
    private Integer parkType;
    private String dataTime;
    private String sign;

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getPlateId() {
        return plateId;
    }

    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getLaneType() {
        return laneType;
    }

    public void setLaneType(Integer laneType) {
        this.laneType = laneType;
    }

    public Integer getFreeBerth() {
        return freeBerth;
    }

    public void setFreeBerth(Integer freeBerth) {
        this.freeBerth = freeBerth;
    }

    public Integer getParkType() {
        return parkType;
    }

    public void setParkType(Integer parkType) {
        this.parkType = parkType;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
