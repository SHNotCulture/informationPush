package com.eparking.informationPush.entity.shImportExpo;
//上海进博会
public class Heart {
    private Integer totalArrived;
    private Integer totalLeft;
    private Integer freeBerth;
    private Long dataTime;
    private String sign;

    public Integer getTotalArrived() {
        return totalArrived;
    }

    public void setTotalArrived(Integer totalArrived) {
        this.totalArrived = totalArrived;
    }

    public Integer getTotalLeft() {
        return totalLeft;
    }

    public void setTotalLeft(Integer totalLeft) {
        this.totalLeft = totalLeft;
    }

    public Integer getFreeBerth() {
        return freeBerth;
    }

    public void setFreeBerth(Integer freeBerth) {
        this.freeBerth = freeBerth;
    }

    public Long getDataTime() {
        return dataTime;
    }

    public void setDataTime(Long dataTime) {
        this.dataTime = dataTime;
    }



    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
