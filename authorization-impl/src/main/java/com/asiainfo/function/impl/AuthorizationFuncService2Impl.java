//package com.asiainfo.function.impl;
//
//import com.asiainfo.annotations.RemoteService;
//import com.asiainfo.base.ResponseEnum;
//import com.asiainfo.business.AuthorizationBusiService;
//import com.asiainfo.entitys.AuthorizationRequest;
//import com.asiainfo.entitys.AuthorizationResponse;
//import com.asiainfo.function.AuthorizationFuncService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * @Author: Ares
// * @Date: 2019/6/1 13:53
// * @Description: 鉴权接口功能层实现2
// * 用于测试有多个同版本服务时的情形
// * @Version: JDK 1.8
// */
//@RemoteService(version = "1.0.1")
//@Service
//public class AuthorizationFuncService2Impl implements AuthorizationFuncService
//{
//    private static final Logger logger = LoggerFactory.getLogger(AuthorizationFuncService2Impl.class);
//
//    @Autowired
//    AuthorizationBusiService authorizationBusiService;
//
//    @Override
//    public AuthorizationResponse authorization(AuthorizationRequest request)
//    {
//        AuthorizationResponse response = new AuthorizationResponse();
//        logger.info("开始执行功能方法2");
//        logger.info("当前用户为{}", request.getUserName());
//        authorizationBusiService.authorization(request.getAuthorization());
//        logger.info("执行功能方法2完毕");
//        response.setAuthorization(request.getAuthorization());
//        response.setResponseEnum(ResponseEnum.SUCCESS);
//        return response;
//    }
//}
