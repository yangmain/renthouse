package com.asiainfo.entitys;

import java.util.List;
import java.util.Map;

/**
 * @Author: Ares
 * @Date: 2019/6/11 18:55
 * @Description: 鉴权业务操作实体
 * @Version: JDK 1.8
 */
public class Authorization
{
    /**
     * 是否通过
     */
    private Boolean pass;

    /**
     * 测试list传参
     */
    private List testList;
    /**
     * 测试map传参
     */
    private Map testMap;

    public Boolean getPass()
    {
        return pass;
    }

    public void setPass(Boolean pass)
    {
        this.pass = pass;
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
