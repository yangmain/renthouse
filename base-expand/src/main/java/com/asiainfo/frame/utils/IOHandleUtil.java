package com.asiainfo.frame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author: Ares
 * @Date: 2018/12/24 15:58
 * @Description: IO操作流通用的关闭工具
 * @Version: JDK 1.8
 */
public class IOHandleUtil
{
    private static final Logger logger = LoggerFactory.getLogger(IOHandleUtil.class);

    public static void closeIOSteam(AutoCloseable closeable)
    {
        if (null != closeable)
        {
            try
            {
                closeable.close();
            } catch (SQLException e)
            {
                logger.error("关闭sql相关连接或对象时失败: ", e);
            } catch (IOException e)
            {
                logger.error("关闭IO操作流时失败: ", e);
            } catch (Exception e)
            {
                logger.error("IO通用关闭方法调用出错: ", e);
            }
        }
    }
}
