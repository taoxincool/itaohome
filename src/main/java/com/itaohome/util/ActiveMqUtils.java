package com.itaohome.util;

import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * Created by Mr tao on 2015/2/2.
 */
public class ActiveMqUtils {

    public static final String brokerURL = "tcp://localhost:61616";//MQ的TCP连接地址

    public static Session session;//MQ的session

    //创建消息发送者
    public static  MessageProducer producer;//消息发送者

}
