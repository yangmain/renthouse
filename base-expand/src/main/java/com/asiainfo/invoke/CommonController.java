package com.asiainfo.invoke;

import com.asiainfo.base.ResponseBase;
import com.asiainfo.base.ResponseEnum;
import com.asiainfo.exceptions.RemoteInvokeException;
import com.asiainfo.utils.ClassTypeUtil;
import com.asiainfo.utils.DateUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.mutable.MutableBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    }

    /**
     * 代理方法Map
     */
    private static final MultiValueMap<String, RemoteProxyService> PROXY_SERVICE_MAP = new LinkedMultiValueMap<>();

    /**
     * @Author: Ares
     * @Description: 添加代理方法
     * @Date: 2019/6/13 10:59
     * @Param: [uniqueKey, service] 请求参数
     * @return: void 响应参数
     */
    public static void addProxyMethod(String uniqueKey, RemoteProxyService proxyService)
    {
        PROXY_SERVICE_MAP.add(uniqueKey, proxyService);
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
        List<RemoteProxyService> proxyServices = PROXY_SERVICE_MAP.get(Objects.requireNonNull(parameters.getFirst("uniqueKey")).toString());
        if (null == proxyServices)
        {
            logger.error(ResponseEnum.INVOKE_FAILURE_NOT_FOUND_SERVICE.getResponseDesc());
            response.setResponseEnum(ResponseEnum.INVOKE_FAILURE_NOT_FOUND_SERVICE);
            return response;
        }
        if (proxyServices.size() > 1)
        {
            logger.error(ResponseEnum.INVOKE_FAILURE_MORE_THAN_ONE.getResponseDesc());
            response.setResponseEnum(ResponseEnum.INVOKE_FAILURE_MORE_THAN_ONE);
            return response;
        }
        RemoteProxyService service = proxyServices.get(0);
        if (null == service || null == service.getProxyService() || null == service.getProxyMethod())
        {
            logger.error(ResponseEnum.INVOKE_FAILURE_NOT_FOUND_SERVICE.getResponseDesc());
            response.setResponseEnum(ResponseEnum.INVOKE_FAILURE_NOT_FOUND_SERVICE);
            return response;
        }
        Method method = service.getProxyMethod();
        // 可以省略,为了预防权限问题这里设置一下
        method.setAccessible(true);
        try
        {
            Class<?>[] paramTypes = method.getParameterTypes();

            Object[] params = new Object[paramTypes.length];
            // 参数处理
            for (int i = 0; i < paramTypes.length; i++)
            {
                Object value = parameters.getFirst("arg" + i);
                MutableBoolean flag = new MutableBoolean(false);
                params[i] = paramHandle(paramTypes[i], value, flag);
            }

            return method.invoke(service.getProxyService(), params);
        } catch (Exception e)
        {
            logger.error("{}: ", ResponseEnum.INVOKE_FAILURE.getResponseDesc(), e);
            response.setResponseEnum(ResponseEnum.INVOKE_FAILURE);
        }
        return response;
    }


    /**
     * @Author: Ares
     * @Description: 参数处理
     * @Date: 2019/6/15 16:59
     * @Param: [requestType, value, flag]
     * 请求Class,值,json操作标识
     * @return: java.lang.Object 响应参数
     */
    private Object paramHandle(Class<?> requestType, Object value, MutableBoolean flag) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParseException
    {
        if (value != null)
        {
            // 如果是基础类型,基本类型不会为null,否则编译器会报错
            if (requestType.isPrimitive())
            {
                switch (requestType.getName())
                {
                    case "int":
                        return Integer.valueOf(value.toString());
                    case "double":
                        return Double.valueOf(value.toString());
                    case "float":
                        return Float.valueOf(value.toString());
                    case "long":
                        return Long.valueOf(value.toString());
                    case "char":
                        return value.toString().charAt(1);
                    case "byte":
                        return Byte.valueOf(value.toString());
                    case "short":
                        return Short.valueOf(value.toString());
                    case "boolean":
                        return Boolean.valueOf(value.toString());
                    default:
                        break;
                }
            }
            // 基础类型包装类
            else if (ClassTypeUtil.isBaseWrap(requestType))
            {
                // 对Character做特殊处理
                if (requestType.getName().contains("Character"))
                {
                    return value.toString().charAt(1);
                }
                else
                {
                    Class<?> clazz = Class.forName(requestType.getName());
                    Method valueOfMethod = clazz.getMethod("valueOf", String.class);
                    return valueOfMethod.invoke(null, value.toString());
                }
            }// 字符串
            else if (String.class.isAssignableFrom(requestType))
            {
                String tempValue = String.valueOf(value);
                // 泛型擦除中Date类型会丢失,这里采用一种比较简单粗暴的方法处理
                if (tempValue.contains(DateUtil.T_VALUE) && tempValue.contains(DateUtil.TIME_SUFFIX))
                {
                    try
                    {
                        return DateUtil.parse(tempValue);
                    } catch (Exception e)
                    {
                        logger.warn("{}: {}", ResponseEnum.INVOKE_FAILURE_DATE_ERROR.getResponseDesc(), tempValue);
                    }
                }
                return tempValue;
            }
            else if (Date.class.isAssignableFrom(requestType))
            {
                return DateUtil.parse(value.toString().replaceAll("\"", ""));
            }
            else if (LocalDateTime.class.isAssignableFrom(requestType))
            {
                return LocalDateTime.parse(value.toString().replaceAll("\"", ""));
            }
            // Map
            else if (Map.class.isAssignableFrom(requestType))
            {
                try
                {
                    Class<?> clazz = Class.forName(requestType.getName());
                    String str;
                    if (flag.booleanValue())
                    {
                        str = objectMapper.writeValueAsString(value);
                    }
                    else
                    {
                        str = value.toString();
                    }
                    Object object = objectMapper.readValue(str, clazz);
                    flag.setValue(true);
                    Method method = clazz.getMethod("entrySet");
                    Set<Map.Entry> entrySet = (Set<Map.Entry>) method.invoke(object);

                    Map map;
                    if (clazz.isInterface())
                    {
                        // 默认实现类选取HashMap
                        map = new HashMap();
                    }
                    else
                    {
                        map = (Map) clazz.newInstance();
                    }
                    for (Map.Entry entry : entrySet)
                    {
                        Object key = entry.getKey();
                        Object tempValue = entry.getValue();
                        map.put(null == key ? null : paramHandle(key.getClass(), key, flag), null == tempValue ? null : paramHandle(tempValue.getClass(), tempValue, flag));
                    }
                    return map;
                } catch (IOException e)
                {
                    logger.error("{}: ", ResponseEnum.INVOKE_FAILURE_JSON_PARSE.getResponseDesc(), e);
                    throw new RemoteInvokeException(ResponseEnum.INVOKE_FAILURE_JSON_PARSE);
                } catch (InstantiationException e)
                {
                    logger.error("", e);
                    throw new RemoteInvokeException(ResponseEnum.INVOKE_FAILURE);
                }
            }// 集合
            else if (Collection.class.isAssignableFrom(requestType))
            {
                try
                {
                    Class<?> clazz = Class.forName(requestType.getName());
                    String str;
                    if (flag.booleanValue())
                    {
                        str = objectMapper.writeValueAsString(value);
                    }
                    else
                    {
                        str = value.toString();
                    }
                    Collection valueCollection = objectMapper.readValue(str, Collection.class);
                    flag.setValue(true);
                    Collection collection;

                    if (requestType.isInterface() && List.class.isAssignableFrom(requestType))
                    {
                        collection = new ArrayList();
                    }
                    else if (requestType.isInterface() && Set.class.isAssignableFrom(requestType))
                    {
                        collection = new HashSet();
                    }
                    else
                    {
                        collection = (Collection) clazz.newInstance();
                    }
                    for (Object element : valueCollection)
                    {
                        collection.add(null == element ? null : paramHandle(element.getClass(), element, flag));
                    }
                    return collection;
                } catch (InstantiationException | IOException e)
                {
                    logger.error("", e);
                    throw new RemoteInvokeException(ResponseEnum.INVOKE_FAILURE);
                }
            }
            else
            {
                Class<?> clazz = Class.forName(requestType.getName());
                try
                {
                    return objectMapper.readValue(value.toString(), clazz);
                } catch (IOException e)
                {
                    logger.error("{}: ", ResponseEnum.INVOKE_FAILURE_JSON_PARSE.getResponseDesc(), e);
                    throw new RemoteInvokeException(ResponseEnum.INVOKE_FAILURE_JSON_PARSE);
                }
            }
        }
        return null;
    }

}
