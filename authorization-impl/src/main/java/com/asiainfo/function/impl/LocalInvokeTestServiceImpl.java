package com.asiainfo.function.impl;

import com.asiainfo.annotations.RemoteService;
import com.asiainfo.entitys.AuthorizationRequest;
import com.asiainfo.entitys.AuthorizationResponse;
import com.asiainfo.function.AuthorizationFuncService;
import com.asiainfo.function.LocalInvokeTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Ares
 * @Date: 2019/6/13 14:31
 * @Description: 测试本地调用, 如果是同一个工程的服务调用走本地不走远程
 * @Version: JDK 1.8
 */
@RemoteService
@Service
public class LocalInvokeTestServiceImpl implements LocalInvokeTestService
{
    private static final Logger logger = LoggerFactory.getLogger(LocalInvokeTestServiceImpl.class);
    @Autowired
    AuthorizationFuncService authorizationFuncService;

    /**
     * @Author: Ares
     * @Description: 测试本地调用, 如果是同一个工程的服务调用走本地不走远程
     * @Date: 2019/6/13 14:33
     * @Param: [requestBase] 请求参数
     * @return: com.asiainfo.base.ResponseBase 响应参数
     */
    @Override
    public AuthorizationResponse localInvokeTest(AuthorizationRequest request)
    {
        logger.info("开始测试本地调用");
        return null;
//        return authorizationFuncService.authorization(request);
    }
}
