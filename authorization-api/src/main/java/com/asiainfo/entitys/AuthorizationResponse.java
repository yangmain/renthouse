package com.asiainfo.entitys;

import com.asiainfo.base.ResponseBase;

/**
 * @Author: Ares
 * @Date: 2019/6/11 18:27
 * @Description: 鉴权响应实体
 * @Version: JDK 1.8
 */
public class AuthorizationResponse extends ResponseBase
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
