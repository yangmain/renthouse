package com.asiainfo.frame.invoke;

import java.lang.reflect.Method;

/**
 * @Author: Ares
 * @Date: 2019/6/1 16:36
 * @Description: 远程代理服务
 * @Version: JDK 1.8
 */
public class RemoteProxyService
{
    /**
     * 服务id
     */
    private String serviceId;
    /**
     * 远程接口全名
     */
    private String remoteInfcName;
    /**
     * 服务中心名
     */
    private String serviceCenter;

    /**
     * 代理服务,服务实现bean
     */
    private Object proxyService;
    /**
     * 代理方法,服务实现中的方法
     */
    private Method proxyMethod;

    public String getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(String serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getRemoteInfcName()
    {
        return remoteInfcName;
    }

    public void setRemoteInfcName(String remoteInfcName)
    {
        this.remoteInfcName = remoteInfcName;
    }

    public String getServiceCenter()
    {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter)
    {
        this.serviceCenter = serviceCenter;
    }

    public Object getProxyService()
    {
        return proxyService;
    }

    public void setProxyService(Object proxyService)
    {
        this.proxyService = proxyService;
    }

    public Method getProxyMethod()
    {
        return proxyMethod;
    }

    public void setProxyMethod(Method proxyMethod)
    {
        this.proxyMethod = proxyMethod;
    }
}
