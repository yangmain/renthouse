package com.asiainfo.base;

import com.asiainfo.exceptions.RemoteInvokeException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

/**
 * @Author: Ares
 * @Date: 2019/6/10 17:43
 * @Description: 发起远程调用并封装响应参数
 * @Version: JDK 1.8
 */
@Component
public class RemoteInvokeHandler
{
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RestTemplate restTemplate;

    public <T> T remoteInvoke(final Class<T> remoteService, String serviceCenter)
    {
        @SuppressWarnings(value = "unchecked") T result = (T) Proxy.newProxyInstance(RemoteInvokeHandler.class.getClassLoader(), new Class<?>[]{remoteService}, (proxy, method, args) -> {
            MultiValueMap<String, Object> remoteRequest = new LinkedMultiValueMap<>();

            RemoteRef remoteRef = remoteService.getAnnotation(RemoteRef.class);
            if (null == remoteRef)
            {
                throw new RemoteInvokeException(remoteService.getName() + "接口不是远程开放接口,请加上RemoteRef注解");
            }
            // 远程接口唯一标识,服务名加版本号
            String service = remoteService.getName() + ":" + remoteRef.version();
            remoteRequest.add("remoteService", DigestUtils.md5DigestAsHex(service.getBytes(StandardCharsets.UTF_8)));

            Class<?>[] paramTypes = method.getParameterTypes();
            // 远程方法唯一标识,方法名
            String methodName = method.getName() + ":";
            StringJoiner joiner = new StringJoiner(":");
            for (int i = 0; i < paramTypes.length; i++)
            {
                // 放置方法请求参数
                remoteRequest.add("arg" + i, args[i]);
                // 将方法名和请求参数类型拼接,形成唯一标识
                joiner.add(paramTypes[i].getName());
            }
            remoteRequest.add("remoteMethod", DigestUtils.md5DigestAsHex((methodName + joiner).getBytes(StandardCharsets.UTF_8)));

            // 发送http请求至其它内部实例,因为用的是实例名eureka会自动负载
            Object returnObject = restTemplate.postForObject("http://" + serviceCenter + "/common/innerInvoke", remoteRequest, method.getReturnType());
            return returnObject;
        });
        return result;
    }
}
