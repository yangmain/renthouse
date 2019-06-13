package com.asiainfo.function.impl;

import com.asiainfo.annotations.RemoteService;
import com.asiainfo.base.ResponseEnum;
import com.asiainfo.business.AuthorizationBusiService;
import com.asiainfo.entitys.AuthorizationRequest;
import com.asiainfo.entitys.AuthorizationResponse;
import com.asiainfo.function.AuthorizationFuncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Ares
 * @Date: 2019/6/1 13:53
 * @Description: 鉴权接口功能层实现
 * @Version: JDK 1.8
 */
@RemoteService
@Service
public class AuthorizationFuncServiceImpl implements AuthorizationFuncService
{
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationFuncServiceImpl.class);

    @Autowired
    AuthorizationBusiService authorizationBusiService;

    @Override
    public AuthorizationResponse authorization(AuthorizationRequest request)
    {
        AuthorizationResponse response = new AuthorizationResponse();
        logger.info("开始执行功能方法");
        logger.info("当前用户为{}", request.getUserName());
        authorizationBusiService.authorization(request.getAuthorization());
        logger.info("执行功能方法完毕");
        response.setAuthorization(request.getAuthorization());
        response.setResponseEnum(ResponseEnum.SUCCESS);
        return response;
    }
}
