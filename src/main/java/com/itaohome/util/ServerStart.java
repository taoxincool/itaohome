package com.itaohome.util;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by Mr tao on 2015/2/2.
 */
public class ServerStart {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        WebAppContext context = new WebAppContext();
        context.setContextPath("/itaohome");
        context.setWar("D:\\idea\\WeChat\\target\\wechat.war");
        server.setHandler(context);
        server.start();
        server.join();

    }
}