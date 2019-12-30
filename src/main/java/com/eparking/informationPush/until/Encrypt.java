package com.eparking.informationPush.until;
//import com.sun.org.apache.xml.internal.security.utils.Base64;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

//import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Encrypt {

    /**
     * Description:MD5工具生成token  遵义
     * @param value
     * @return
     */
    public static String getMD5Value(String value){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] md5ValueByteArray = messageDigest.digest(value.getBytes());
            BigInteger bigInteger = new BigInteger(1 , md5ValueByteArray);
            return bigInteger.toString(16).toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 生成签名  遵义
     * @param map
     * @return
     */
    public static String getSignToken(Map<String, String> map,String publicKey) {
        String result = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造签名键值对的格式
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (item.getKey() != null || item.getKey() != "") {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (!(val == "" || val == null)) {
                        sb.append(key + "=" + val + "&");
                    }
                }
            }
            result = sb.toString()+"key="+publicKey+"";
            //进行MD5加密
            result = getMD5Value(result);
        } catch (Exception e) {
            return null;
        }
        return result;
    }
    /**
     * 云南加密
     */
        private static final String MAC_NAME = "HmacSHA1";
        private static final String ENCODING = "UTF-8";

        /**
         * calculate the signature
         */
        public static String genSignature(String appId, String appKey, String random) {
            String[] arr = new String[] { appId, appKey, random };
// 将appId、appKey、appKey三个参数进行字典序排序
            Arrays.sort(arr);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]);
            }
            MessageDigest md = null;
            String tmpStr = null;
            try {
                md = MessageDigest.getInstance("SHA-1");
// 将三个参数字符串拼接成一个字符串进行sha1加密
                byte[] digest = md.digest(sb.toString().getBytes());
                tmpStr = byte2Str(digest);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tmpStr;
        }
        /**
         * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
         *
         * @param encryptText 被签名的字符串
         * @param encryptKey 密钥
         * @return
         * @throws Exception
         */
        public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
            byte[] data = encryptKey.getBytes(ENCODING);
// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
// 生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance(MAC_NAME);
// 用给定密钥初始化 Mac 对象
            mac.init(secretKey);
            byte[] text = encryptText.getBytes(ENCODING);
// 完成 Mac 操作
            return mac.doFinal(text);
        }

        private static String toString(byte[] rawHmac) {
            StringBuilder sb = new StringBuilder();
            for (byte b : rawHmac) {
                sb.append(byteToHexString(b));
            }
            return sb.toString();
        }
        private static String byteToHexString(byte ib) {
            char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
            char[] ob = new char[2];
            ob[0] = Digit[(ib >>> 4) & 0X0f];
            ob[1] = Digit[ib & 0X0F];
            String s = new String(ob);
            return s;
        }
        private static String byte2Str(byte[] bs) {
            char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
            int j = bs.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = bs[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        }


    public static void main(String[] args) throws Exception {
            String api = "https://tgw.wiiqq.com/test/park_api/parking_lot/report_parking_lot_num";
            String str = "{\"appid\":\"tchsrhtcc\",\"total_number\":\"100\",\"ts\":\"1526546565\"}";
            String encryptKey = "yyn_fwpt_tchsrhtcc_api_key";
            byte[] b = HmacSHA1Encrypt(api + str, encryptKey);
            System.out.println(b);
            try {
                //String sign = Base64.encode(b);
                String sign = Base64.encodeBase64String(b);
                sign = URLEncoder.encode(sign, "UTF-8");
                System.out.println(sign);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    /**
     * 生成签名 上海进博会
     * @param map
     * @return
     */
    public static String getSignJinBoHui(Map<String, String> map,String publicKey) {
        String result = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造签名键值对的格式
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (item.getKey() != null || item.getKey() != "") {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (!(val == "" || val == null)) {
                        sb.append(val);
                    }
                }
            }
            result = publicKey+sb.toString();
            //进行MD5加密
            result = MD5Util.getMD5(result,false,32);
            /*result = getMD5Value(result);*/
        } catch (Exception e) {
            return null;
        }
        return result;
    }


}
