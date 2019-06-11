package com.asiainfo.business;

import com.asiainfo.entitys.Authorization;

/**
 * @Author: Ares
 * @Date: 2019/6/1 9:23
 * @Description: 鉴权业务层接口
 * @Version: JDK 1.8
 */
public interface AuthorizationBusiService
{
   /**
    * @Author: Ares
    * @Description: 鉴权
    * @Date: 2019/6/11 14:31
    * @Param: [] 请求参数
    * @return: void 响应参数
    */
   Authorization authorization(Authorization authorization);
}
