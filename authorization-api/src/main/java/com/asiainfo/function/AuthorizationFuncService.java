package com.asiainfo.function;

import com.asiainfo.annotations.RemoteInfc;
import com.asiainfo.entitys.AuthorizationRequest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     *
     * @return: void 响应参数
     */
    List<List<List<String>>>   authorization(AuthorizationRequest request, int i, long l, short s, float f, byte b, char c, boolean bool, double d, Integer ii, Long ll, Short ss, Float ff, Byte bb, Character cc, Boolean boolBool, Date date, String str, Object[] array, String[] strs, Map map, HashMap map1, Map<String, String> map2, Map<String, Date> map3, List list, Map<String, List<AuthorizationRequest>> map11, List<Map<String, List<AuthorizationRequest>>> ares, List<List<List>> lists, LocalDateTime localDateTime);
}
