package com.itaohome.ws;

import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Mr tao on 2015/1/13.
 */
@Component
@WebService(serviceName="WeChartService")
public class WeChartService {

    @WebMethod(operationName="checkToken")
    public String checkToken(String signature,String timestamp,String nonce,String echostr){
        System.out.println("进入方法体!");
        return echostr;
    }

}
