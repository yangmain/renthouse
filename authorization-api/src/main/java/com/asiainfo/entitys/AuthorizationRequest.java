package com.asiainfo.entitys;

import com.asiainfo.base.RequestBase;

/**
 * @Author: Ares
 * @Date: 2019/6/11 17:36
 * @Description: 鉴权请求实体
 * @Version: JDK 1.8
 */
public class AuthorizationRequest extends RequestBase
{
    private Authorization authorization;

    public Authorization getAuthorization()
    {
        return authorization;
    }

    public void setAuthorization(Authorization authorization)
    {
        this.authorization = authorization;
    }
}
