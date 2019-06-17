package com.asiainfo.frame.base;

/**
 * @Author: Ares
 * @Date: 2019/6/11 17:39
 * @Description: 响应基类
 * @Version: JDK 1.8
 */
public class ResponseBase
{
    /**
     * 响应码
     */
    private String responseCode;
    /**
     * 响应描述
     */
    private String responseDesc;

    public String getResponseCode()
    {
        return responseCode;
    }

    public void setResponseCode(String responseCode)
    {
        this.responseCode = responseCode;
    }

    public String getResponseDesc()
    {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc)
    {
        this.responseDesc = responseDesc;
    }

    public void setResponseEnum(ResponseEnum responseEnum)
    {
        this.responseCode = responseEnum.getResponseCode();
        this.responseDesc = responseEnum.getResponseDesc();
    }

    @Override
    public String toString()
    {
        return "ResponseBase{" + "responseCode='" + responseCode + '\'' + ", responseDesc='" + responseDesc + '\'' + '}';
    }
}
