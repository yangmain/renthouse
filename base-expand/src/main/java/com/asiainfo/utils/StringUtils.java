package com.asiainfo.utils;

/**
 * @author Ares
 * @date 2018/6/1 10:44
 */
public class StringUtils
{
    public static boolean isNotEmpty(String s)
    {
        return null != s && !s.isEmpty();
    }

    public static boolean isEmpty(String s)
    {
        return null == s || s.isEmpty();
    }

    /**
     * @Author: Ares
     * @Description: 下划线转大驼峰式
     * @Date: 2019/6/13 17:31
     * @Param: [source] 请求参数
     * @return: java.lang.String 响应参数
     */
    public static String underlineToBigCamelCase(String source)
    {
        String[] strs = source.split("_");
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : strs)
        {
            stringBuffer.append(upperFirst(s.toLowerCase()));
        }
        return String.valueOf(stringBuffer);
    }

    /**
     * @Author: Ares
     * @Description: 下划线转小驼峰式
     * @Date: 2019/6/13 17:31
     * @Param: [source] 请求参数
     * @return: java.lang.String 响应参数
     */
    public static String underlineToLittleCamelCase(String source)
    {
        return lowerFirst(underlineToBigCamelCase(source));
    }

    /**
     * @Author: Ares
     * @Description: 首字母大写
     * @Date: 2019/6/13 17:31
     * @Param: [source] 请求参数
     * @return: java.lang.String 响应参数
     */
    public static String upperFirst(String source)
    {
        char[] chars = source.toCharArray();
        chars[0] = 97 <= chars[0] && chars[0] <= 122 ? (char) (chars[0] - 32) : chars[0];
        return String.valueOf(chars);
    }

    /**
     * @Author: Ares
     * @Description: 首字母小写
     * @Date: 2019/6/13 17:33
     * @Param: [source] 请求参数
     * @return: java.lang.String 响应参数
     */
    public static String lowerFirst(String source)
    {
        char[] chars = source.toCharArray();
        chars[0] = 65 <= chars[0] && chars[0] <= 90 ? (char) (chars[0] + 32) : chars[0];
        return String.valueOf(chars);
    }
}
