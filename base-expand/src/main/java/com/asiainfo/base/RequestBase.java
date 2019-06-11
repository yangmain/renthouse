package com.asiainfo.base;

/**
 * @Author: Ares
 * @Date: 2019/6/11 17:37
 * @Description: 请求基类
 * @Version: JDK 1.8
 */
public class RequestBase
{
    private String userName;
    private String password;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "RequestBase{" + "userName='" + userName + '\'' + ", password='" + password + '\'' + '}';
    }
}
