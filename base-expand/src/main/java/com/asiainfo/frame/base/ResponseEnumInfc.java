package com.asiainfo.frame.base;

/**
 * @Author: Ares
 * @Date: 2019/6/13 12:04
 * @Description:
 * @Version: JDK 1.8
 */
public interface ResponseEnumInfc
{
    /**
     * @Author: Ares
     * @Description: 获取响应码
     * @Date: 2019/6/13 12:04
     * @Param: [] 请求参数
     * @return: java.lang.String 响应参数
     */
    String getResponseCode();

    /**
     * @Author: Ares
     * @Description: 获取响应描述
     * @Date: 2019/6/13 12:05
     * @Param: [] 请求参数
     * @return: java.lang.String 响应参数
     */
    String getResponseDesc();
}
