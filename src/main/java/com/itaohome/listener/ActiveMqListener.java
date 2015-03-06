package com.itaohome.listener;

import com.itaohome.util.ActiveMqUtils;

import javax.jms.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Created by Mr tao on 2015/1/30.
 */
public class ActiveMqListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获取MQ连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMqUtils.brokerURL);
        try {
            //获取MQ连接对象
            Connection connection = connectionFactory.createConnection();
            //连接MQ
            connection.start();
            //获取MQ的sesion
            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //初始化util中的MQ Session和消息发送者
            ActiveMqUtils.session = session;
            ActiveMqUtils.producer = session.createProducer(null);

            /**接受消息*/
            //获取消息发送目的地标示,发送给这个地址也就是我的
            //final Destination destination = session.createTopic("192.168.5.105");//通过主题方式发送消息，订阅此主题的人都可以收到该消息。
            final Destination destination = session.createQueue("192.168.5.105");//通过队列发送消息，只发一人
            //创建消息接受者对象，通过目的地对象
            MessageConsumer messageConsumer = session.createConsumer(destination);
            //监听发送给我的消息内容
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        MapMessage map = (MapMessage) message;
                        String method = map.getString("method");
                        String methodType = map.getString("methodType");
                        if(methodType == null) {
                            //表示调用的方法
                            System.out.println("消息队列监听到的方法名字为:" + method);

                            //回复消息
                            MapMessage replyMessage = ActiveMqUtils.session.createMapMessage();
                            replyMessage.setString("method", method);
                            replyMessage.setString("methodType", "return");
                            replyMessage.setString("status","哥，已经接收到您的消息了，这是我给你回复的内容:");
                            //发送消息
                            ActiveMqUtils.producer.send(destination, replyMessage);
                            System.out.println("回复消息消息成功!");
                        } else {
                            //表示对方回复我们的方法
                        }
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("MQ消息监听成功!");
        } catch (JMSException e) {
            System.out.println("监听MQ消息出现异常:" + e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
