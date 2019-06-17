package com.asiainfo.portal.controller;

import com.asiainfo.frame.base.RequestBase;
import com.asiainfo.frame.base.ResponseBase;
import com.asiainfo.strategy.function.SharingStrategyFuncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Ares
 * @Date: 2019/6/17 10:53
 * @Description:
 * @Version: JDK 1.8
 */
@RestController
@RequestMapping("/remote")
public class RemoteController
{
    @Autowired
    SharingStrategyFuncService sharingStrategyFuncService;


    @RequestMapping("/test")
    public ResponseBase test(RequestBase request)
    {
        return sharingStrategyFuncService.sharingStrategy(request);
    }
}
