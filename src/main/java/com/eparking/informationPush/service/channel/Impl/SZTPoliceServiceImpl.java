package com.eparking.informationPush.service.channel.Impl;

import com.eparking.informationPush.entity.system.ParkInOut;
import com.eparking.informationPush.entity.system.ParkRouteConf;
import com.eparking.informationPush.entity.system.RouteInfo;
import com.eparking.informationPush.entity.szTpolice.TpoliceParkIn;
import com.eparking.informationPush.entity.szTpolice.TpoliceParkOut;
import com.eparking.informationPush.service.channel.SZTPoliceService;
import com.eparking.informationPush.service.system.ParkRouteConfService;
import com.eparking.informationPush.service.system.RouteInfoService;
import com.eparking.informationPush.until.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szjj.tools.algorithmic.RSAUtil;
import szjj.tools.algorithmic.SignatureForSzjjUtil;
import szjj.tools.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.*;

@Service
public class SZTPoliceServiceImpl implements SZTPoliceService {

    @Override
    public void pushParkIn(ParkInOut parkInOut, Integer routeId) {
        RouteInfo routeInfo = Global.RouteInfoMap.get(routeId);
        ParkRouteConf parkRouteConf = Global.parkRouteConfByParkIdRouteIdMap.get(parkInOut.getParkId()+"&"+routeId);
        TpoliceParkIn tpoliceParkIn = new TpoliceParkIn();
        tpoliceParkIn.setCSID(routeInfo.getUsername());
        tpoliceParkIn.setMSGVER(routeInfo.getVerNum());
        tpoliceParkIn.setCSPTLS(parkInOut.getId());
        tpoliceParkIn.setJYLX("carinupload");
        tpoliceParkIn.setQQSJ(DateUtil.getCurDateTime());
        tpoliceParkIn.setTCCID(parkRouteConf.getRouteParkId());
        tpoliceParkIn.setTCCMC(parkRouteConf.getRouteParkName());
        tpoliceParkIn.setTCCJKID(parkRouteConf.getInParkPort());
        //tpoliceParkIn.setJCKBH(BH.toString());
        tpoliceParkIn.setJCKMC(parkInOut.getInPortName());
        tpoliceParkIn.setCPHM(parkInOut.getInCarPlate());
        tpoliceParkIn.setCPLX(getCPLX(parkInOut.getInCarPlateColor()));
        tpoliceParkIn.setTGSJ(parkInOut.getInTime());
        tpoliceParkIn.setZXD("");//置信度
        tpoliceParkIn.setGKJIP("");//图片存放IP地址
        List<String> picList = new ArrayList<>();
        String picTPoliceUrl = parkInOut.getInPicPath().substring(21,35)+"00"+parkRouteConf.getRouteParkId()+"11"+"_0_0_0_0"+parkInOut.getInPicPath().substring(parkInOut.getInPicPath().length()-4);
        picList.add(picTPoliceUrl);
        tpoliceParkIn.setTPIDS(JsonUtil.listToJson(picList));
        tpoliceParkIn.setTPCSCC("1");
        tpoliceParkIn.setCB("");
        tpoliceParkIn.setCSYS("");
        tpoliceParkIn.setSFWC("1");
        tpoliceParkIn.setBCBZ("1");
        tpoliceParkIn.setTCCCWS(Global.parkTotalNumMap.get(parkInOut.getParkId()).toString());//总停车数
        tpoliceParkIn.setZCCLS(String.valueOf(Global.parkTotalNumMap.get(parkInOut.getParkId())-Global.parkFreeNumMap.get(parkInOut.getParkId())));//在场车辆数
        tpoliceParkIn.setSYCWS(Global.parkFreeNumMap.get(parkInOut.getParkId()).toString());//剩余车辆数
        tpoliceParkIn.setYKBZ(getCarNature(parkInOut.getCarNature()));
        tpoliceParkIn.setSJH("");
        tpoliceParkIn.setDKPT("99");
        //回调地址
        tpoliceParkIn.setHTTPHDDZ("192.168.10.107");
        Map map = com.eparking.informationPush.until.StringUtil.object2Map(tpoliceParkIn);
        map.put("MAC",showMAC(JsonUtil.beanToJson(tpoliceParkIn)));
        String resultMsg = HttpUtil.doPostJson(routeInfo.getInPath(),JsonUtil.mapToJson(map));
        Map resultMsgMap = JsonUtil.json2Map(resultMsg);
        String RCCLJG = resultMsgMap.get("RCCLJG").toString();
        if (RCCLJG.equals("0000")){
            pushPic(parkInOut.getInPicPath(),picTPoliceUrl);
            Global.parkRouteInNumMap.put(parkInOut.getParkId()+"&"+routeId,Global.parkRouteInNumMap.get(parkInOut.getParkId()+"&"+routeId)+1);
        }
    }

    @Override
    public void pushParkOut(ParkInOut parkInOut, Integer routeId) {
        RouteInfo routeInfo = Global.RouteInfoMap.get(routeId);
        ParkRouteConf parkRouteConf = Global.parkRouteConfByParkIdRouteIdMap.get(parkInOut.getParkId()+"&"+routeId);
        Map<String, String> map = new HashMap<String, String>();
        TpoliceParkOut tpoliceParkOut = new TpoliceParkOut();
        tpoliceParkOut.setCSID(routeInfo.getUsername());//平台的注册编号
        tpoliceParkOut.setMSGVER(routeInfo.getVerNum());//报文版本
        tpoliceParkOut.setCSPTLS(parkInOut.getId());//单服务商停车场管理系统内唯一
        tpoliceParkOut.setJYLX("caroutupload");
        tpoliceParkOut.setQQSJ(DateUtil.getCurDateTime());//请求时间
        tpoliceParkOut.setTCCID(parkRouteConf.getRouteParkId());//停车场ID
        tpoliceParkOut.setTCCMC(parkRouteConf.getRouteParkName());//停车场名称
        tpoliceParkOut.setTCCCKID(parkRouteConf.getOutParkPort());//停车场出口ID(在申请接入时由平台管理方统一编制发放，平台内唯一)
        //tpoliceParkOut.setJCKBH(BH.toString());//进出口编号
        tpoliceParkOut.setJCKMC(parkInOut.getOutPortName());//进出口名称
        tpoliceParkOut.setCPHM(parkInOut.getOutCarPlate());//车牌号码
        tpoliceParkOut.setCPLX(getCPLX(parkInOut.getOutCarPlateColor()));//车牌类型
        tpoliceParkOut.setTGSJ(parkInOut.getOutTime());//通过时间
        tpoliceParkOut.setZXD("");//置信度
        tpoliceParkOut.setGKJIP("");//图片存放工控机IP
        List<String> picList = new ArrayList<>();
        String picTPoliceUrl = parkInOut.getOutPicPath().substring(21,35)+"00"+parkRouteConf.getRouteParkId()+"11"+"_0_0_0_0"+parkInOut.getOutPicPath().substring(parkInOut.getOutPicPath().length()-4);
        picList.add(picTPoliceUrl);
        tpoliceParkOut.setTPIDS(JsonUtil.listToJson(picList));//机动车辆出场图片的全名列表
        tpoliceParkOut.setTPCSCC("1");//机动车辆出场图片是否由服务商主动上传(0-否，1-是；为0是 TPIDS为图片可访问路径)
        tpoliceParkOut.setCB("");//车标
        tpoliceParkOut.setCSYS("");//车辆颜色
        tpoliceParkOut.setSFWC("1");//是否完成识别
        tpoliceParkOut.setBCBZ("1");//补传标志
        tpoliceParkOut.setTCCCWS(Global.parkTotalNumMap.get(parkInOut.getParkId()).toString());//总停车数
        tpoliceParkOut.setZCCLS(String.valueOf(Global.parkTotalNumMap.get(parkInOut.getParkId())-Global.parkFreeNumMap.get(parkInOut.getParkId())));//在场车辆数
        tpoliceParkOut.setSYCWS(Global.parkFreeNumMap.get(parkInOut.getParkId()).toString());//剩余车辆数
        tpoliceParkOut.setYKBZ(getCarNature(parkInOut.getCarNature()));
        tpoliceParkOut.setRCSJ(parkInOut.getInTime());//入场时间
        tpoliceParkOut.setTCSC(String.valueOf(DateUtil.getInterval(parkInOut.getInTime(),parkInOut.getOutTime(),0)));//停车时长
        //设置mac
        String macJson = tpoliceParkOut.getMacJson();
        tpoliceParkOut.setMAC(showMAC(macJson));
        String jsonParkOut = tpoliceParkOut.toString();
        //System.out.println("测试数据为"+jsonParkOut);
        map.put("data", jsonParkOut);
        String resultMsg = HttpClientUtil.doPost(routeInfo.getOutPath()+"/caroutupload", map);
        Map resultMsgMap = JsonUtil.json2Map(resultMsg);
        String RCCLJG = resultMsgMap.get("RCCLJG").toString();
        if (RCCLJG.equals("0000")){
            pushPic(parkInOut.getOutPicPath(),picTPoliceUrl);
            Global.parkRouteOutNumMap.put(parkInOut.getParkId()+"&"+routeId,Global.parkRouteOutNumMap.get(parkInOut.getParkId()+"&"+routeId)+1);

        }
    }

    /**
     * 获取交警月租车类型
     * @param carNature
     * @return
     */
    private String getCarNature(Integer carNature){
        if (carNature==2){
            //月租车
            return "1";
        }else {
            return "0";
        }
    }

    /**
     * 车牌颜色区分车牌类型
     * @return
     */
    private String getCPLX(String inCarPlateColor){
         String cplx="02";
        if (!StringUtils.isBlank(inCarPlateColor)){
            String firstWord = inCarPlateColor.substring(0, 1);
            switch (firstWord){
                case "绿":
                    cplx = "52";
                    break;
                case "黄":
                    cplx = "01";
                    break;
                default:
                    break;
            }
        }
        return cplx;
    }
    private Integer getBh(){
        return null;
    }

    private void pushPic(String picCybUrl,String picTPoliceStr) {
        /*ObsClient obsClient = new ObsClient(Global.ak, Global.sk, Global.endPoint);
        try {
            InputStream inputStream = new URL("http://eparking2.oss-cn-shenzhen.aliyuncs.com" + picCybUrl).openStream();
            if (inputStream.available() != 0) {
                //FileUtil.writeToLocal("/alidata/apache-tomcat-8.5.30/img/demo.jpg", inputStream);
                PutObjectRequest request = new PutObjectRequest();
                request.setBucketName("szjjtcccheyiboprod6t7iyukhj");
                request.setObjectKey(DateUtil.getCurDate() + "/" + picTPoliceStr);
                request.setInput(inputStream);
                //request.setFile(new File("/alidata/apache-tomcat-8.5.30/img/demo.jpg"));
                // 设置对象访问权限为公共读
                request.setAcl(AccessControlList.REST_CANNED_PUBLIC_READ);
                obsClient.putObject(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private String showMAC(String data){
        //公钥模
         String modulus = "91C4EC4110C8800A7BE06FD1886783F85BF6FF0936EFC2517DC3B6B97664FE3239B62A95E6E97FD6CB501340D76C8D32F431A66D08919BF680A84E156B4FF8E08E18AD56ED13850265F9389EEB97BC723F500CA88B4F0AB3953FA886A606ECC7B0C5E82AE8D38B0C49058991EB68B26C05724B92877B042AF9D2F8428265CBA7";
        //私钥指数
         String exponent = "5F282C09F588CD7D0A8CE2B1E7D79B60DFB80798DE51F989F50D7414F1E2CFC5BDC2DB5DEBD575ECA3E06C11220FECC4DC8024E27C7B8152937E4B2AD458EA5FBB5A06EE211446229B0968E49F6EB6F2AFD60259803AD1D57D5E88A40D7D706EA57486D8F4E77ED4E1A07C71C976804BF2135C5B4D28833C2267D58EBE15AC81";

//        Gson gson = new Gson();
//        Map map = JsonUtil.json2Map(data1);
        RSAPublicKey publicKey;
        RSAPrivateKey privateKey;
        String MACvalue="";
        try {
            publicKey = RSAUtil.loadPublicKey(modulus, "10001", 16);
            privateKey = RSAUtil.loadPrivateKey(modulus, exponent, 16);
//            System.out.println("a---"+publicKey.toString());
//            System.out.println("b---"+privateKey.toString());
            //String data = gson.toJson(map);
            byte[] signData = SignatureForSzjjUtil.signature(data,"UTF-8", privateKey, "");
            MACvalue= StringUtil.bytesToHexString(signData).toUpperCase();
//            System.out.println("签名数据=["+ MACvalue +"]");
            //验证公密钥
            boolean signVerify = SignatureForSzjjUtil.signVerified(data,"UTF-8", signData, publicKey, "");
//            System.out.println("验签结果=["+signVerify+"]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MACvalue;
    }

    public static void main(String[] args) {

    }
}
