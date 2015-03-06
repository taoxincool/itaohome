package com.itaohome.util;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mr tao on 2015/1/13.
 */
public class WeUtils {

    /**
     * SHA1加密
     *
     * @param decript
     * @return
     */
    public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取access_token
     *
     * @return
     */
    public static String getAccessToken() {
        String result = null;
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String params = "grant_type=client_credential&appid=" + Constants.APPID + "&secret=" + Constants.APPSECRET;
        String responseBody = HttpUtils.doGet(url, params, "GBK", true);
        if (StringUtils.isNotBlank(responseBody)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> map = mapper.readValue(responseBody, new TypeReference<Map<String, String>>() {
                });
                result = map.get("access_token");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取整形时间
     *
     * @return
     */
    public static String getIntegerDate() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * 获取异常行数
     *
     * @param e
     * @return
     */
    public static StringBuffer getTraceInfo(Exception e) {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stacks = e.getStackTrace();
        for (int i = 0; i < stacks.length; i++) {
            if (stacks[i].getClassName().contains("test")) {
                sb.append("class: ").append(stacks[i].getClassName())
                        .append("; method: ").append(stacks[i].getMethodName())
                        .append("; line: ").append(stacks[i].getLineNumber())
                        .append(";  Exception: ");
                break;
            }
        }
        return sb;
    }

    /**
     * 获取异常行数和异常信息
     *
     * @param e
     * @return
     */
    public static String getExceptionMessage(Exception e) {
        String message = e.toString();
        if (message.lastIndexOf(":") != -1)
            message = message.substring(0, message.lastIndexOf(":"));
        return getTraceInfo(e).append(message).toString();
    }

    public static void main(String[] args) {
        //模拟post
        String url = "https://api.weixin.qq.com/datacube/getusersummary";
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", getAccessToken());
        params.put("begin_date", "2015-01-12");
        params.put("end_date", "2015-01-16");
        String result = HttpUtils.doPost(url, params, "GBK", true);
        System.out.println(result);
    }

}
