package com.itaohome.web;

import com.itaohome.model.TbLog;
import com.itaohome.repository.AutoReplyRepository;
import com.itaohome.repository.LogRepository;
import com.itaohome.service.WeService;
import com.itaohome.util.XmlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Mr tao on 2015/1/13.
 */
@Controller
@RequestMapping(value = "/t")
public class TestController {

    @Resource
    private LogRepository logRepository;

    @Resource
    private WeService weService;

    @Resource
    private AutoReplyRepository autoReplyRepository;

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @ResponseBody
    public String testSave() {
        TbLog log = new TbLog();
        log.setMemo("12412444");
        log.setType("news");
        logRepository.save(log);
        return "1";
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public String querySave() {
        List<TbLog> logs = logRepository.queryList();
        return "1";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String testDb() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            /*****1. 填写数据库相关信息(请查找数据库详情页)*****/
            String databaseName = "XXNKzIxwFlWumpDqyulA";
            String host = "sqld.duapp.com";
            String port = "4050";
            String username = "Pm0oDFGj75mdWcce3njfH8jw";//用户名(api key);
            String password = "Ts9pBrBjnwKLbZTEpBB3qfEvzuRpUELG";//密码(secret key)
            String driverName = "com.mysql.jdbc.Driver";
            String dbUrl = "jdbc:mysql://";
            String serverName = host + ":" + port + "/";
            String connName = dbUrl + serverName + databaseName;

            /******2. 接着连接并选择数据库名为databaseName的服务器******/
            Class.forName(driverName);
            connection = DriverManager.getConnection(connName, username, password);
            stmt = connection.createStatement();
            /*至此连接已完全建立，就可对当前数据库进行相应的操作了*/
            /* 3. 接下来就可以使用其它标准mysql函数操作进行数据库操作*/
            //创建一个数据库表
            sql = "create table if not exists test_mysql(" +
                    "id int primary key auto_increment," +
                    "no int, " +
                    "name varchar(1024)," +
                    "key idx_no(no))";
            stmt.execute(sql);
        } catch (Exception e) {
            return "出错了" + e;
        }
        return "数据库链接成功哦!hahahahahahahaha!";
    }

    @RequestMapping(value = "/sm", method = RequestMethod.GET)
    @ResponseBody
    public String sm() {

        //自动回复消息内容
        String result = "<xml>" +
                "<ToUserName><![CDATA[itaohome]]></ToUserName>" +
                "<FromUserName><![CDATA[oOFybuBwfARvFgGJzGoM7iDQzdTE]]></FromUserName>" +
                "<CreateTime>2010115</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[text content]]></Content>" +
                "</xml>";
        Document doc = XmlUtils.getXmlByString(result);
        String returnMsg = weService.saveFans(doc);
        return returnMsg;
    }

    @RequestMapping(value = "/chinese", method = RequestMethod.GET)
    @ResponseBody
    public String chinese() {
        String result = "";
        try {
            String msgContent = autoReplyRepository.getSubscribeAutoReplyMsg() == null ? "" : autoReplyRepository.getSubscribeAutoReplyMsg().getReplyContent();
            String customContent = "我的中文哦!!!";
            result = "数据库：" + msgContent + " ==============  自定义:" + customContent;
           // result = new String(result.getBytes("gbk"),"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    @ResponseBody
    public String text(){
        //自动回复消息内容
        String result = "<xml>" +
                "<ToUserName><![CDATA[itaohome]]></ToUserName>" +
                "<FromUserName><![CDATA[oOFybuBwfARvFgGJzGoM7iDQzdTE]]></FromUserName>" +
                "<CreateTime>2010115</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[图文]]></Content>" +
                "</xml>";
        Document doc = XmlUtils.getXmlByString(result);
        String xml  = weService.disposeTextMessage(doc);
        System.out.println(xml);
        return xml;

        /*TbAutoReply tbAutoReply = autoReplyRepository.getTextAutoReplyMsg("图文");
        return  tbAutoReply.getReplyContent();*/
    }

}
