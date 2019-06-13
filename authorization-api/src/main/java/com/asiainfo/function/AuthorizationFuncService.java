package com.asiainfo.function;

import com.asiainfo.annotations.RemoteInfc;
import com.asiainfo.entitys.AuthorizationRequest;
import com.asiainfo.entitys.AuthorizationResponse;

/**
 * @Author: Ares
 * @Date: 2019/6/1 9:20
 * @Description: 鉴权功能层接口
 * @Version: JDK 1.8
 */
@RemoteInfc
public interface AuthorizationFuncService
{
    /**
     * @Author: Ares
     * @Description: 鉴权
     * @Date: 2019/6/1 9:21
     * @Param: [] 请求参数
     * @return: void 响应参数
     */
    AuthorizationResponse authorization(AuthorizationRequest request);
}
