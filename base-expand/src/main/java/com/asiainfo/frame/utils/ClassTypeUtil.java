package com.asiainfo.frame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Ares
 * @Date: 2019/5/8 17:11
 * @Description: Class类型处理工具
 * @Version: JDK 1.8
 */
public class ClassTypeUtil
{
    private static final Logger logger = LoggerFactory.getLogger(ClassTypeUtil.class);
    /**
     * 基础类型包装类列表
     */
    private static final List<String> BASE_WRAP_TYPE_LIST = new ArrayList<>();
    /**
     * 基础类型列表
     */
    private static final List<String> BASE_TYPE_LIST = new ArrayList<>();

    static
    {
        BASE_TYPE_LIST.add("int");
        BASE_TYPE_LIST.add("double");
        BASE_TYPE_LIST.add("long");
        BASE_TYPE_LIST.add("short");
        BASE_TYPE_LIST.add("byte");
        BASE_TYPE_LIST.add("boolean");
        BASE_TYPE_LIST.add("char");
        BASE_TYPE_LIST.add("float");

        BASE_WRAP_TYPE_LIST.add("java.lang.Integer");
        BASE_WRAP_TYPE_LIST.add("java.lang.Double");
        BASE_WRAP_TYPE_LIST.add("java.lang.Float");
        BASE_WRAP_TYPE_LIST.add("java.lang.Long");
        BASE_WRAP_TYPE_LIST.add("java.lang.Short");
        BASE_WRAP_TYPE_LIST.add("java.lang.Byte");
        BASE_WRAP_TYPE_LIST.add("java.lang.Boolean");
        BASE_WRAP_TYPE_LIST.add("java.lang.Character");
    }

    /**
     * @Author: Ares
     * @Description: 判断是否为基础类型
     * @Date: 2019/5/8 17:48
     * @Param: [className] Class名称
     * @return: boolean 响应参数
     **/
    public static boolean isPrimitive(String className)
    {
        //基本类型、包装类型、String类型
        return BASE_TYPE_LIST.contains(className);
    }

    /**
     * @Author: Ares
     * @Description: 判断是否是基础类型包装类
     * @Date: 2019/6/14 10:01
     * @Param: [className] Class名称
     * @return: boolean 响应参数
     */
    public static boolean isBaseWrap(String className)
    {
        return BASE_WRAP_TYPE_LIST.contains(className);
    }

    /**
     * @Author: Ares
     * @Description: 判断是否是基础类型包装类
     * @Date: 2019/6/14 10:01
     * @Param: [clazz] Class
     * @return: boolean 响应参数
     */
    public static boolean isBaseWrap(Class clazz)
    {
        return isBaseWrap(clazz.getCanonicalName());
    }

    /**
     * @Author: Ares
     * @Description: 判断是否是基础类型或基础类型包装类
     * @Date: 2019/6/14 10:07
     * @Param: [className] Class名称
     * @return: boolean 响应参数
     */
    public static boolean isBaseOrWrap(String className)
    {
        return isPrimitive(className) || isBaseWrap(className);
    }

    /**
     * @Author: Ares
     * @Description: 判断是否是基础类型或基础类型包装类
     * @Date: 2019/6/14 10:08
     * @Param: [clazz] Class
     * @return: boolean 响应参数
     */
    public static boolean isBaseOrWrap(Class clazz)
    {
        return isBaseOrWrap(clazz.getCanonicalName());
    }

    /**
     * @Author: Ares
     * @Description: 判断是否是基础类型或基础类型包装类
     * @Date: 2019/6/14 10:09
     * @Param: [object] 对象
     * @return: boolean 响应参数
     */
    public static boolean isBaseOrWrap(Object object)
    {
        return null != object && isBaseOrWrap(object.getClass());
    }
}
