package com.asiainfo.entitys;

import com.asiainfo.base.RequestBase;

import java.util.List;
import java.util.Map;

/**
 * @Author: Ares
 * @Date: 2019/6/11 17:36
 * @Description: 鉴权请求实体
 * @Version: JDK 1.8
 */
public class AuthorizationRequest extends RequestBase
{
    private Authorization authorization;

    /**
     * 测试list传参
     */
    private List testList;
    /**
     * 测试map传参
     */
    private Map testMap;

    public Authorization getAuthorization()
    {
        return authorization;
    }

    public void setAuthorization(Authorization authorization)
    {
        this.authorization = authorization;
    }

    public List getTestList()
    {
        return testList;
    }

    public void setTestList(List testList)
    {
        this.testList = testList;
    }

    public Map getTestMap()
    {
        return testMap;
    }

    public void setTestMap(Map testMap)
    {
        this.testMap = testMap;
    }
}
