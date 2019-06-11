package com.asiainfo.controller;

import com.asiainfo.entitys.AuthorizationRequest;
import com.asiainfo.entitys.AuthorizationResponse;
import com.asiainfo.function.AuthorizationFuncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Ares
 * @Date: 2019/6/11 14:46
 * @Description: 测试远程调用
 * @Version: JDK 1.8
 */
@RestController
@RequestMapping(value = "/remote")
public class RemoteController
{
    private static final Logger logger = LoggerFactory.getLogger(RemoteController.class);

    @Autowired
    private AuthorizationFuncService authorizationFuncService;

    @RequestMapping(value = "/authorization")
    public AuthorizationResponse authorization(@RequestBody AuthorizationRequest request)
    {
        logger.info("开始测试远程调用");
        return authorizationFuncService.authorization(request);
    }

}
