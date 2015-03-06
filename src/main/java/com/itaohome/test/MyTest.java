package com.itaohome.test;

import com.itaohome.service.WeService;
import com.itaohome.util.HttpUtils;
import com.itaohome.util.WeUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mr tao on 2015/1/15.
 */
public class MyTest {

    @Test
    public void testSubs(){
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("D:\\idea\\test\\itaohome\\src\\main\\webapp\\WEB-INF\\spring\\spring-servlet.xml");
        WeService weService = (WeService) applicationContext.getBean("weService");
        //System.out.println("===============================================password is " + userService.findByUsername("1015").getPassword());
    }

    @Test
    public void testExc(){
        try {
            int a = 1/0;
        } catch (Exception e) {
            System.out.println(WeUtils.getExceptionMessage(e));
            //System.out.println(getExceptionMessage(e));
        }

    }

    @Test
    public void testToken() {

        //模拟get
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/token";
            String params = "grant_type=client_credential&appid=wx4e0465dd6a4be55a&secret=8afad22962affafae48032d068900627";
            //String params = "grant_type=client_credential&appid=wxbf82e34cbe52dd15&secret=45e99767bc5a58c7b80d66524d109fec";
            String responseBody = HttpUtils.doGet(url, params, "GBK", true);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(responseBody, new TypeReference<Map<String, String>>() {
            });
            String result = map.get("access_token");
            System.out.println(result);

            String url2 = "https://api.weixin.qq.com/datacube/getusercumulate?access_token="+result;
            //String url2 = "https://api.weixin.qq.com/datacube/getusercumulate";
            Map<String, String> params2 = new HashMap<String, String>();
            //params2.put("access_token", result);
            params2.put("begin_date","2015-01-01");
            params2.put("end_date","2015-01-17");
            String result2 = HttpUtils.doPost(url2, params2, "GBK", true);
            System.out.println(result2);
        } catch (IOException e) {
            e.printStackTrace();
        }

       /* //模拟post
        String url = "https://api.weixin.qq.com/datacube/getusersummary";
        Map<String, String> params2 = new HashMap<String, String>();
        params.put("access_token", getAccessToken());
        params.put("begin_date","2014-12-02");
        params.put("end_date","2014-12-07");
        String result = doPost( url,params2,"GBK", true);
        System.out.println(result);*/
    }

    @Test
    public  void testPost() throws IOException {

        //连接地址
        String surl = "http://localhost:8080/webak/j_spring_security_check";

        /**
         * 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using
         *  java.net.URL and //java.net.URLConnection
         */
        URL url = new URL(surl);
        URLConnection connection = url.openConnection();

        /**
         * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
         * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
         */
        connection.setDoOutput(true);
        /**
         * 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
         */
        OutputStreamWriter out = new OutputStreamWriter(connection
                .getOutputStream(), "UTF-8");
        out.write("j_username=admin&j_password=admin"); //post的关键所在！
        // remember to clean up
        out.flush();
        out.close();
        /**
         * 这样就可以发送一个看起来象这样的POST：
         * POST /jobsearch/jobsearch.cgi HTTP 1.0 ACCEPT:
         * text/plain Content-type: application/x-www-form-urlencoded
         * Content-length: 99 username=bob password=someword
         */
        // 一旦发送成功，用以下方法就可以得到服务器的回应：
        String sCurrentLine;
        String sTotalString;
        sCurrentLine = "";
        sTotalString = "";
        InputStream l_urlStream;
        l_urlStream = connection.getInputStream();
        // 传说中的三层包装阿！
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(
                l_urlStream));
        while ((sCurrentLine = l_reader.readLine()) != null) {
            sTotalString += sCurrentLine + "\r\n";

        }
        System.out.println(sTotalString);
    }

    @Test
    public void login() throws IOException {
        String usr = "admin", pwd = "admin";

        StringBuilder sbR = new StringBuilder();

        // 访问URL，并把信息存入sb中
        // 如果服务端登录成功后，服务端的代码调用下面的代码
        // response.sendRedirect("welcome.jsp");
        // 则会不成功，原因(Step2，没有上传jsessionid值，导致没session)如下
        // Step1[login.jsp登录成功]->转到->
        // Step2[welcome.jsp不能得到session，判断没有登录成功]->转到->Step3[login.jsp要求用户登录]

        // 连接地址
        String surl = "http://localhost:8080/webak/j_spring_security_check";
        URL url = new URL(surl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);// 允许连接提交信息
        connection.setRequestMethod("POST");// 网页默认“GET”提交方式

        StringBuffer sb = new StringBuffer();
        sb.append("j_username=" + usr);
        sb.append("&j_password=" + pwd);
        connection.setRequestProperty("Content-Length",
                String.valueOf(sb.toString().length()));

        OutputStream os = connection.getOutputStream();
        os.write(sb.toString().getBytes());
        os.close();

        // 取Cookie
        BufferedReader br = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String responseCookie;// 标示Session必须
        responseCookie = connection.getHeaderField("Set-Cookie");// 取到所用的Cookie
        System.out.println("cookie:" + responseCookie);

        // 取返回的页面
        String line = br.readLine();
        while (line != null) {
            sbR.append(line);
            line = br.readLine();
        }

        System.out.println(sbR.toString());
    }

    @Test
    public void testHashCode(){
        String node1 = "node1";
        String node2 = "node2";
        String node3 = "node3";
        System.out.println(node1.hashCode());
        System.out.println(node2.hashCode());
        System.out.println(node3.hashCode());
    }

}
