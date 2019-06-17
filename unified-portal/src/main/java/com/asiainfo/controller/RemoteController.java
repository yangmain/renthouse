package com.asiainfo.controller;

import com.asiainfo.entitys.AuthorizationRequest;
import com.asiainfo.function.AuthorizationFuncService;
import com.asiainfo.function.LocalInvokeTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private LocalInvokeTestService localInvokeTestService;

    @RequestMapping(value = "/authorization")
    public List<List<List<String>>> authorization(@RequestBody AuthorizationRequest request)
    {
        logger.info("开始测试远程调用");

        Object[] objects = new Object[2];
        objects[0] = "array";
        objects[1] = 1;
        String[] arrayUnused = new String[]{"arrayUnused", "arrayUnused2"};
        List listUnused = new ArrayList();
        listUnused.add("listUnused");

        Map mapUnused = new HashMap<>();
        HashMap hashMap = new HashMap<>();
        hashMap.put("hashKey", 1);
        mapUnused.put("mapKeyUnused", 1.0);
        Map<String, String> map2 = new HashMap<>();
        map2.put("ares", "xue");
        Map<String, Date> map3 = new HashMap<>();
        map3.put("hai", new Date());

        Date date = new Date();
        String parameterUnused = "parameterUnused";
        short s = 3;

        Map<String, List<AuthorizationRequest>> map = new HashMap<>();
        List<AuthorizationRequest> list = new ArrayList<>();
        AuthorizationRequest authorizationRequest = new AuthorizationRequest();
        authorizationRequest.setUserName("11111111111");
        list.add(authorizationRequest);
        map.put("hello", list);

        List<Map<String, List<AuthorizationRequest>>> ares = new ArrayList<>();
        ares.add(map);

        List<List<List>> finalList = new ArrayList<>();
        List<List> lists = new ArrayList<>();
        finalList.add(lists);
        List list1 = new ArrayList();
        lists.add(list1);
        list1.add("success");

        LocalDateTime localDateTime = LocalDateTime.now();
        return authorizationFuncService.authorization(request, 1, 2L, s, 4.0f, (byte) 5, 'a', true, 8.0d, new Integer("10000"), new Long("10001"), new Short("10002"), new Float("10003.0f"), new Byte("127"), new Character('b'), new Boolean("true"), date, parameterUnused, objects, arrayUnused, mapUnused, hashMap, map2, map3, listUnused, map, ares, finalList, localDateTime);
    }

}