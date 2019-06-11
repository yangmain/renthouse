package com.asiainfo.utils;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

/**
 * @Author: Ares
 * @Date: 2019/6/1 15:16
 * @Description: 动态代理工具类
 * @Version: JDK 1.8
 */
public class DynamicProxyUtil
{
    /**
     * @Author: Ares
     * @Description: 获取目标对象
     * @Date: 2019/6/1 15:18
     * @Param: [proxy] 请求对象
     * @return: java.lang.Object 真实对象,如不是代理则是其本身
     **/
    public static Object getTarget(Object proxy) throws Exception
    {
        if (!AopUtils.isAopProxy(proxy))
        {
            //不是代理对象
            return proxy;
        }

        if (AopUtils.isJdkDynamicProxy(proxy))
        {
            //如果是JDK代理
            return getJdkDynamicProxyTargetObject(proxy);
        }
        else
        {
            //如果是cglib代理
            return getCglibProxyTargetObject(proxy);
        }
    }

    /**
     * @Author: Ares
     * @Description: 获取cglib代理的目标对象
     * @Date: 2019/6/1 15:19
     * @Param: [proxy] 请求参数
     * @return: java.lang.Object 响应参数
     **/
    private static Object getCglibProxyTargetObject(Object proxy) throws Exception
    {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);

        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        return ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
    }

    /**
     * @Author: Ares
     * @Description: 获取JDK代理的目标对象
     * @Date: 2019/6/1 15:19
     * @Param: [proxy] 请求参数
     * @return: java.lang.Object 响应参数
     **/
    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception
    {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);

        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        return ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
    }
}
