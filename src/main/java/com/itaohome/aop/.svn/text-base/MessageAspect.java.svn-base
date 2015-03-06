package com.itaohome.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by Mr tao on 2015/1/23.
 */
@Component
@Aspect
public class MessageAspect {

    //方法执行的前后调用
    //@AfterReturning(value = "execution (* com.itaohome.service.impl.*.*(..))", returning = "result")
    @AfterReturning(value = "execution (* com.itaohome.repository.impl.*.*(..))", returning = "result")
    public void repo(JoinPoint point, String result) throws Throwable {
        String method = point.getSignature().getName();
        System.out.println("===================================r方法名：" + method);

        Object[] ars = point.getArgs();
        for (Object ar : ars) {
            System.out.println("===================================r参数为:" + ar);
        }

        System.out.println("===================================r返回值为:" + result);
    }

    //方法执行的前后调用
    @AfterReturning(value = "execution (* com.itaohome.service.impl.*.*(..))", returning = "result")
    public void service(JoinPoint point, String result) throws Throwable {
        String method = point.getSignature().getName();
        System.out.println("===================================s方法名：" + method);

        Object[] ars = point.getArgs();
        for (Object ar : ars) {
            System.out.println("===================================s参数为:" + ar);
        }

        System.out.println("===================================s返回值为:" + result);
    }


}
