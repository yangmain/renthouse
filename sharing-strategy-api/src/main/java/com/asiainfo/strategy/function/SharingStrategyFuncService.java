package com.asiainfo.strategy.function;


import com.asiainfo.frame.annotations.RemoteInfc;
import com.asiainfo.frame.base.RequestBase;
import com.asiainfo.frame.base.ResponseBase;

/**
 * @Author: Ares
 * @Date: 2019/6/13 15:28
 * @Description: 合租攻略接口
 * @Version: JDK 1.8
 */
@RemoteInfc
public interface SharingStrategyFuncService
{
    /**
     * @Author: Ares
     * @Description: 合租攻略
     * @Date: 2019/6/17 10:20
     * @Param: [request] 请求参数
     * @return: ResponseBase 响应参数
     */
    ResponseBase sharingStrategy(RequestBase request);
}
