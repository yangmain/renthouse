package com.asiainfo.function.impl;

import com.asiainfo.annotations.RemoteService;
import com.asiainfo.base.ResponseEnum;
import com.asiainfo.business.AuthorizationBusiService;
import com.asiainfo.entitys.AuthorizationRequest;
import com.asiainfo.entitys.AuthorizationResponse;
import com.asiainfo.function.AuthorizationFuncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Ares
 * @Date: 2019/6/1 13:53
 * @Description: 鉴权接口功能层实现
 * @Version: JDK 1.8
 */
@RemoteService
@Service
public class AuthorizationFuncServiceImpl implements AuthorizationFuncService
{
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationFuncServiceImpl.class);

    @Autowired
    AuthorizationBusiService authorizationBusiService;

    @Override
    public List<List<List<String>>> authorization(
            AuthorizationRequest request, int i, long l, short s, float f, byte b, char c, boolean bool, double d, Integer ii, Long ll, Short ss, Float ff, Byte bb, Character cc, Boolean boolBool, Date date, String str, Object[] array, String[] strs, Map map, HashMap map1, Map<String, String> map2, Map<String, Date> map3, List list, Map<String, List<AuthorizationRequest>> map11, List<Map<String, List<AuthorizationRequest>>> ares, List<List<List>> lists)
    {
        AuthorizationResponse response = new AuthorizationResponse();
        logger.info("开始执行功能方法");
        logger.info(str);
        logger.info(Arrays.toString(array));
        logger.info(Arrays.toString(strs));
        logger.info("" + map3.get("hai").getTime());
        logger.info("当前用户为{}", request.getUserName());
        logger.info(map11.get("hello").toString());
        logger.info(ares.get(0).toString());
        logger.info(ares.get(0).get("hello").toString());
        logger.info(lists.get(0).toString());
        logger.info(lists.get(0).get(0).toString());
        logger.info(lists.get(0).get(0).get(0).toString());
        authorizationBusiService.authorization(request.getAuthorization());
        logger.info("执行功能方法完毕");
        response.setAuthorization(request.getAuthorization());
        response.setResponseEnum(ResponseEnum.SUCCESS);

        List<List<List<String>>> listArrayList = new ArrayList<>();
        List<List<String>> listArrayList1 = new ArrayList<>();
        listArrayList.add(listArrayList1);
        List<String> result = new ArrayList<>();
        result.add("222222222");
        listArrayList1.add(result);
        return listArrayList;
    }
}
