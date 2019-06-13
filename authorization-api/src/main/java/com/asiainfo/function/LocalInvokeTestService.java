package com.asiainfo.function;

import com.asiainfo.annotations.RemoteInfc;
import com.asiainfo.entitys.AuthorizationRequest;
import com.asiainfo.entitys.AuthorizationResponse;

/**
 * @Author: Ares
 * @Date: 2019/6/13 14:30
 * @Description: 测试本地调用, 如果是同一个工程的服务调用走本地不走远程
 * @Version: JDK 1.8
 */
@RemoteInfc
public interface LocalInvokeTestService
{
    /**
     * @Author: Ares
     * @Description: 测试本地调用, 如果是同一个工程的服务调用走本地不走远程
     * @Date: 2019/6/13 14:31
     * @Param: [requestBase] 请求参数
     * @return: com.asiainfo.base.ResponseBase 响应参数
     */
    AuthorizationResponse localInvokeTest(AuthorizationRequest request);
}
