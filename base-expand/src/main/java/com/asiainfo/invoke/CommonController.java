package com.asiainfo.invoke;

import com.asiainfo.base.ResponseBase;
import com.asiainfo.base.ResponseEnum;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: Ares
 * @Date: 2019/5/31 16:40
 * @Description: 公共请求入口
 * @Version: JDK 1.8
 */
@RestController
@RequestMapping(value = "/common")
public class CommonController
{
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static
    {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 代理方法Map
     */
    private static final Map<String, RemoteProxyService> PROXY_SERVICE_MAP = new HashMap<>();

    /**
     * @Author: Ares
     * @Description: 添加代理方法
     * @Date: 2019/6/13 10:59
     * @Param: [uniqueKey, service] 请求参数
     * @return: void 响应参数
     */
    public static void addProxyMethod(String uniqueKey, RemoteProxyService proxyService)
    {
        PROXY_SERVICE_MAP.put(uniqueKey, proxyService);
    }

    /**
     * @Author: Ares
     * @Description: 统一调用地址
     * @Date: 2019/6/10 20:21
     * @Param: [request] 请求参数
     * @return: java.lang.Object 响应参数
     */
    @RequestMapping(value = "/invoke")
    public Object invoke(HttpServletRequest request)
    {
        logger.info(request.getParameter("serviceId"));
        return null;
    }

    /**
     * @Author: Ares
     * @Description: 内部实例互相调用
     * @Date: 2019/6/10 20:22
     * @Param: [request] 请求参数
     * @return: java.lang.Object 响应参数
     **/
    @RequestMapping(value = "/innerInvoke")
    public Object innerInvoke(@RequestParam(required = false) MultiValueMap<String, Object> parameters)
    {
        ResponseBase response = new ResponseBase();
        RemoteProxyService service = PROXY_SERVICE_MAP.get(Objects.requireNonNull(parameters.getFirst("uniqueKey")).toString());
        if (null == service || null == service.getProxyService())
        {
            response.setResponseEnum(ResponseEnum.INVOKE_FAILURE_NOT_FOUND_SERVICE);
            return response;
        }
        Method method = service.getProxyMethod();
        // 可以省略,为了预防权限问题这里设置一下
        method.setAccessible(true);
        try
        {
            Class<?>[] paramTypes = method.getParameterTypes();
            String requestType = paramTypes[0].getName();
            Class<?> clazz = Class.forName(requestType);
            Object requestParam = objectMapper.readValue(Objects.requireNonNull(parameters.getFirst(requestType)).toString(), clazz);
            return method.invoke(service.getProxyService(), requestParam);
        } catch (Exception e)
        {
            logger.error("远程调用失败: ", e);
            response.setResponseEnum(ResponseEnum.INVOKE_FAILURE);
        }
        return response;
    }
}
