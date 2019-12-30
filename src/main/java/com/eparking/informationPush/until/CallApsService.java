package com.eparking.informationPush.until;

import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@Service
public class CallApsService {
    private Socket s;
    private DataOutputStream out;

    /*public CallApsService() throws IOException {
    }*/

    public static void main(String[] args) throws Exception {
        CallApsService c = new CallApsService();
        int i = 0;
        //Map<String, String> infoMap = initInput();
        /*for(Entry<String, String> enty: infoMap.entrySet()){
            i++;
            c.talk(enty.getKey(),enty.getValue(),i);
            System.out.println("发送信息完毕!");
        }*/
    }

    // 发送对象
    // ObjectOutputStream oos;
    // TransferObj obj;
    public String sendMessage(Socket s,String xmlReq) {
        String returnMsg = "";
        try {
            s.setSoTimeout(10000);
            // socket传字符串
            out = new DataOutputStream(s.getOutputStream());
            byte[] cypt = {0x02};
            byte[] cypte = {0x03};
            byte[] bt = xmlReq.getBytes();
            byte[] send = StringUtil.byteMergerAll(cypt, bt, cypte);

            System.out.println("管委会发送报文："+xmlReq);
            out.write(send,0,send.length);
            //读取服务器端数据
            DataInputStream input = new DataInputStream(s.getInputStream());
            byte[] b = new byte[1024]; //容器，存放数据
            int len = input.read(b);
            if(len != -1){
                //System.out.println("返回数据: "+b.toString());
                byte []retlen = new byte[1024];
                System.arraycopy(b,1,retlen,len-1,len-2);
                System.out.println("管委会返回报文体: "+new String(retlen,"UTF-8"));
                input.close();
                returnMsg = new String(retlen,"UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnMsg;

    }

    public String talk(String xmlReq) throws Exception {
        String msg="";
        try {
            // 发送对象
            s = new Socket("180.168.9.178", 15000);
            System.out.println("客户端:发送信息");
            msg = sendMessage(s,xmlReq);
            System.out.println("发送信息完毕!");
            // 发字符串
//			 receiveMessage(s);
            out.close();
            // in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (s != null)
                    s.close(); // 断开连接
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }




    /*private static byte[] byteMergerAll(byte[]... values) {
        int length_byte = 0;
        for (int i = 0; i < values.length; i++) {
            length_byte += values[i].length;
        }
        byte[] all_byte = new byte[length_byte];
        int countLength = 0;
        for (int i = 0; i < values.length; i++) {
            byte[] b = values[i];
            System.arraycopy(b, 0, all_byte, countLength, b.length);
            countLength += b.length;
        }
        return all_byte;
    }*/


}
