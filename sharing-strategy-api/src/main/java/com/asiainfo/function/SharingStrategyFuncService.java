package com.asiainfo.function;

import com.asiainfo.annotations.RemoteInfc;
import com.asiainfo.base.RequestBase;
import com.asiainfo.base.ResponseBase;

/**
 * @Author: Ares
 * @Date: 2019/6/13 15:28
 * @Description:
 * @Version: JDK 1.8
 */
@RemoteInfc
public interface SharingStrategyFuncService
{
    ResponseBase sharingStrategy(RequestBase request);
}
