package com.asiainfo.strategy.function.impl;

import com.asiainfo.frame.annotations.RemoteService;
import com.asiainfo.frame.base.RequestBase;
import com.asiainfo.frame.base.ResponseBase;
import com.asiainfo.frame.base.ResponseEnum;
import com.asiainfo.strategy.function.SharingStrategyFuncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: Ares
 * @Date: 2019/6/17 10:19
 * @Description: 合租攻略实现
 * @Version: JDK 1.8
 */
@RemoteService
@Service
public class SharingStrategyFuncServiceImpl implements SharingStrategyFuncService
{
    private static final Logger logger = LoggerFactory.getLogger(SharingStrategyFuncServiceImpl.class);

    /**
     * @Author: Ares
     * @Description: 合租攻略
     * @Date: 2019/6/17 10:20
     * @Param: [request] 请求参数
     * @return: ResponseBase 响应参数
     */
    @Override
    public ResponseBase sharingStrategy(RequestBase request)
    {
        logger.info("调用功能服务");
        ResponseBase response = new ResponseBase();
        response.setResponseEnum(ResponseEnum.SUCCESS);
        return response;
    }
}
