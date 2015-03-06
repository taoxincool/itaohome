package com.itaohome.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebClient {
    public static String getWebContent(String urlString, final String charset, int timeout) throws IOException {
        if (urlString == null || urlString.length() == 0) {
            return null;
        }
        urlString = (urlString.startsWith("http://") || urlString
                .startsWith("https://")) ? urlString : ("http://" + urlString)
                .intern();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn
                .setRequestProperty(
                        "User-Agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");//增加报头，模拟浏览器，防止屏蔽
        conn.setRequestProperty("Accept", "text/html");//只接受text/html类型，当然也可以接受图片,pdf,*/*任意，就是tomcat/conf/web里面定义那些
        conn.setConnectTimeout(timeout);
        try {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        InputStream input = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input,
                charset));
        String line = null;
        StringBuffer sb = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("/r/n");
        }
        if (reader != null) {
            reader.close();
        }
        if (conn != null) {
            conn.disconnect();
        }
        return sb.toString();

    }

    public static String getWebContent(String urlString) throws IOException {
        return getWebContent(urlString, "iso-8859-1", 5000);
    }

    public static void main(String[] args) throws IOException {
        String s = getWebContent("http://www.sina.com");
        s = new String(s.getBytes("iso-8859-1"), "gb2312");
        System.out.println(s);
    }
}
