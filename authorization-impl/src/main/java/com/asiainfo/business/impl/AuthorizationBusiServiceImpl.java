package com.asiainfo.business.impl;

import com.asiainfo.business.AuthorizationBusiService;
import com.asiainfo.entitys.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: Ares
 * @Date: 2019/6/1 13:52
 * @Description:
 * @Version: JDK 1.8
 */
@Service
public class AuthorizationBusiServiceImpl implements AuthorizationBusiService
{
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationBusiServiceImpl.class);


    @Override
    public Authorization authorization(Authorization authorization)
    {
        logger.info("执行业务方法");
        logger.info(authorization.getPass().toString());
        authorization.setPass(Boolean.TRUE);
        return authorization;
    }
}
