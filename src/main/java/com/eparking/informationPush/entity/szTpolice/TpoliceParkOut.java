package com.eparking.informationPush.entity.szTpolice;

/**
 * @ClassName TpoliceParkOut
 * @Author jin
 * @Date 2018/5/17 16:01
 **/
public class TpoliceParkOut {
    private String CSID;//平台分配
    private String MSGVER;//报文版本
    private String CSPTLS;//服务商系统记录流水号
    private String JYLX;//caroutupload
    private String QQSJ;//请求时间
    private String TCCID;//停车场ID
    private String TCCMC;//停车场名称
    private String TCCCKID;//停车场出口ID
    private String JCKBH;//进出口编号
    private String JCKMC;//进出口名称
    private String CPHM;//车牌号码
    private String CPLX;//车牌类型
    private String TGSJ;//通过时间
    private String ZXD;//置信度
    private String GKJIP;//图片存放工控号
    private String TPIDS;//
    private String TPCSCC;//
    private String CB;//车标
    private String CSYS;//车辆颜色
    private String SFWC;//是否完成识别
    private String BCBZ;//
    private String TCCCWS;//注册总车位数
    private String ZCCLS;//在场车辆数
    private String SYCWS;//剩余车位数
    private String RCSJ;//入场时间
    private String TCSC;//停车时长
    private String YKBZ;//月卡标志
    private String MAC;//

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"CSID\":\"")
                .append(CSID).append('\"');
        sb.append(",\"MSGVER\":\"")
                .append(MSGVER).append('\"');
        sb.append(",\"CSPTLS\":\"")
                .append(CSPTLS).append('\"');
        sb.append(",\"JYLX\":\"")
                .append(JYLX).append('\"');
        sb.append(",\"QQSJ\":\"")
                .append(QQSJ).append('\"');
        sb.append(",\"TCCID\":\"")
                .append(TCCID).append('\"');
        sb.append(",\"TCCMC\":\"")
                .append(TCCMC).append('\"');
        sb.append(",\"TCCCKID\":\"")
                .append(TCCCKID).append('\"');
        sb.append(",\"JCKBH\":\"")
                .append(JCKBH).append('\"');
        sb.append(",\"JCKMC\":\"")
                .append(JCKMC).append('\"');
        sb.append(",\"CPHM\":\"")
                .append(CPHM).append('\"');
        sb.append(",\"CPLX\":\"")
                .append(CPLX).append('\"');
        sb.append(",\"TGSJ\":\"")
                .append(TGSJ).append('\"');
        sb.append(",\"ZXD\":\"")
                .append(ZXD).append('\"');
        sb.append(",\"GKJIP\":\"")
                .append(GKJIP).append('\"');
        sb.append(",\"TPIDS\":\"")
                .append(TPIDS).append('\"');
        sb.append(",\"TPCSCC\":\"")
                .append(TPCSCC).append('\"');
        sb.append(",\"CB\":\"")
                .append(CB).append('\"');
        sb.append(",\"CSYS\":\"")
                .append(CSYS).append('\"');
        sb.append(",\"SFWC\":\"")
                .append(SFWC).append('\"');
        sb.append(",\"BCBZ\":\"")
                .append(BCBZ).append('\"');
        sb.append(",\"TCCCWS\":\"")
                .append(TCCCWS).append('\"');
        sb.append(",\"ZCCLS\":\"")
                .append(ZCCLS).append('\"');
        sb.append(",\"SYCWS\":\"")
                .append(SYCWS).append('\"');
        sb.append(",\"RCSJ\":\"")
                .append(RCSJ).append('\"');
        sb.append(",\"TCSC\":\"")
                .append(TCSC).append('\"');
        sb.append(",\"YKBZ\":\"")
                .append(YKBZ).append('\"');
        sb.append(",\"MAC\":\"")
                .append(MAC).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public String getMacJson() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"CSID\":\"")
                .append(CSID).append('\"');
        sb.append(",\"MSGVER\":\"")
                .append(MSGVER).append('\"');
        sb.append(",\"CSPTLS\":\"")
                .append(CSPTLS).append('\"');
        sb.append(",\"JYLX\":\"")
                .append(JYLX).append('\"');
        sb.append(",\"QQSJ\":\"")
                .append(QQSJ).append('\"');
        sb.append(",\"TCCID\":\"")
                .append(TCCID).append('\"');
        sb.append(",\"TCCMC\":\"")
                .append(TCCMC).append('\"');
        sb.append(",\"TCCCKID\":\"")
                .append(TCCCKID).append('\"');
        sb.append(",\"JCKBH\":\"")
                .append(JCKBH).append('\"');
        sb.append(",\"JCKMC\":\"")
                .append(JCKMC).append('\"');
        sb.append(",\"CPHM\":\"")
                .append(CPHM).append('\"');
        sb.append(",\"CPLX\":\"")
                .append(CPLX).append('\"');
        sb.append(",\"TGSJ\":\"")
                .append(TGSJ).append('\"');
        sb.append(",\"ZXD\":\"")
                .append(ZXD).append('\"');
        sb.append(",\"GKJIP\":\"")
                .append(GKJIP).append('\"');
        sb.append(",\"TPIDS\":\"")
                .append(TPIDS).append('\"');
        sb.append(",\"TPCSCC\":\"")
                .append(TPCSCC).append('\"');
        sb.append(",\"CB\":\"")
                .append(CB).append('\"');
        sb.append(",\"CSYS\":\"")
                .append(CSYS).append('\"');
        sb.append(",\"SFWC\":\"")
                .append(SFWC).append('\"');
        sb.append(",\"BCBZ\":\"")
                .append(BCBZ).append('\"');
        sb.append(",\"TCCCWS\":\"")
                .append(TCCCWS).append('\"');
        sb.append(",\"ZCCLS\":\"")
                .append(ZCCLS).append('\"');
        sb.append(",\"SYCWS\":\"")
                .append(SYCWS).append('\"');
        sb.append(",\"RCSJ\":\"")
                .append(RCSJ).append('\"');
        sb.append(",\"TCSC\":\"")
                .append(TCSC).append('\"');
        sb.append(",\"YKBZ\":\"")
                .append(YKBZ).append('\"');
        sb.append('}');
        return sb.toString();
    }
    public String getCSID() {
        return CSID;
    }

    public void setCSID(String CSID) {
        this.CSID = CSID;
    }

    public String getMSGVER() {
        return MSGVER;
    }

    public void setMSGVER(String MSGVER) {
        this.MSGVER = MSGVER;
    }

    public String getCSPTLS() {
        return CSPTLS;
    }

    public void setCSPTLS(String CSPTLS) {
        this.CSPTLS = CSPTLS;
    }

    public String getJYLX() {
        return JYLX;
    }

    public void setJYLX(String JYLX) {
        this.JYLX = JYLX;
    }

    public String getQQSJ() {
        return QQSJ;
    }

    public void setQQSJ(String QQSJ) {
        this.QQSJ = QQSJ;
    }

    public String getTCCID() {
        return TCCID;
    }

    public void setTCCID(String TCCID) {
        this.TCCID = TCCID;
    }

    public String getTCCMC() {
        return TCCMC;
    }

    public void setTCCMC(String TCCMC) {
        this.TCCMC = TCCMC;
    }

    public String getTCCCKID() {
        return TCCCKID;
    }

    public void setTCCCKID(String TCCCKID) {
        this.TCCCKID = TCCCKID;
    }

    public String getJCKBH() {
        return JCKBH;
    }

    public void setJCKBH(String JCKBH) {
        this.JCKBH = JCKBH;
    }

    public String getJCKMC() {
        return JCKMC;
    }

    public void setJCKMC(String JCKMC) {
        this.JCKMC = JCKMC;
    }

    public String getCPHM() {
        return CPHM;
    }

    public void setCPHM(String CPHM) {
        this.CPHM = CPHM;
    }

    public String getCPLX() {
        return CPLX;
    }

    public void setCPLX(String CPLX) {
        this.CPLX = CPLX;
    }

    public String getTGSJ() {
        return TGSJ;
    }

    public void setTGSJ(String TGSJ) {
        this.TGSJ = TGSJ;
    }

    public String getZXD() {
        return ZXD;
    }

    public void setZXD(String ZXD) {
        this.ZXD = ZXD;
    }

    public String getGKJIP() {
        return GKJIP;
    }

    public void setGKJIP(String GKJIP) {
        this.GKJIP = GKJIP;
    }

    public String getTPIDS() {
        return TPIDS;
    }

    public void setTPIDS(String TPIDS) {
        this.TPIDS = TPIDS;
    }

    public String getTPCSCC() {
        return TPCSCC;
    }

    public void setTPCSCC(String TPCSCC) {
        this.TPCSCC = TPCSCC;
    }

    public String getCB() {
        return CB;
    }

    public void setCB(String CB) {
        this.CB = CB;
    }

    public String getCSYS() {
        return CSYS;
    }

    public void setCSYS(String CSYS) {
        this.CSYS = CSYS;
    }

    public String getSFWC() {
        return SFWC;
    }

    public void setSFWC(String SFWC) {
        this.SFWC = SFWC;
    }

    public String getBCBZ() {
        return BCBZ;
    }

    public void setBCBZ(String BCBZ) {
        this.BCBZ = BCBZ;
    }

    public String getTCCCWS() {
        return TCCCWS;
    }

    public void setTCCCWS(String TCCCWS) {
        this.TCCCWS = TCCCWS;
    }

    public String getZCCLS() {
        return ZCCLS;
    }

    public void setZCCLS(String ZCCLS) {
        this.ZCCLS = ZCCLS;
    }

    public String getSYCWS() {
        return SYCWS;
    }

    public void setSYCWS(String SYCWS) {
        this.SYCWS = SYCWS;
    }

    public String getRCSJ() {
        return RCSJ;
    }

    public void setRCSJ(String RCSJ) {
        this.RCSJ = RCSJ;
    }

    public String getTCSC() {
        return TCSC;
    }

    public void setTCSC(String TCSC) {
        this.TCSC = TCSC;
    }

    public String getYKBZ() {
        return YKBZ;
    }

    public void setYKBZ(String YKBZ) {
        this.YKBZ = YKBZ;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }
}
