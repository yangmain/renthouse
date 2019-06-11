package com.asiainfo.base;

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
     * 服务全名
     */
    private String serviceName;
    /**
     * 服务版本号
     */
    private String serviceVersion;
    /**
     * 服务中心
     */
    private String serviceCenter;
    /**
     * 被代理方法名
     */
    private String methodName;
    /**
     * 请求实体类型
     */
    private String requestType;

    /**
     * 代理方法
     */
    private Method proxyMethod;
    /**
     * 代理方法
     */
    private Object proxyService;

    public String getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(String serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getServiceVersion()
    {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion)
    {
        this.serviceVersion = serviceVersion;
    }

    public String getServiceCenter()
    {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter)
    {
        this.serviceCenter = serviceCenter;
    }

    public String getMethodName()
    {
        return methodName;
    }

    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }

    public String getRequestType()
    {
        return requestType;
    }

    public void setRequestType(String requestType)
    {
        this.requestType = requestType;
    }

    public Method getProxyMethod()
    {
        return proxyMethod;
    }

    public void setProxyMethod(Method proxyMethod)
    {
        this.proxyMethod = proxyMethod;
    }

    public Object getProxyService()
    {
        return proxyService;
    }

    public void setProxyService(Object proxyService)
    {
        this.proxyService = proxyService;
    }
}
