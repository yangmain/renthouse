package com.asiainfo.base;

import com.asiainfo.utils.DynamicProxyUtil;
import com.asiainfo.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.beans.Introspector;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Ares
 * @Date: 2019/6/1 14:18
 * @Description: 远程服务调用扩展配置类
 * @Version: JDK 1.8
 */
@Configuration
public class RemoteServiceConfig
{
    private static final Logger logger = LoggerFactory.getLogger(RemoteServiceConfig.class);

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * @Author: Ares
     * @Description: 初始化方法
     * @Date: 2019/6/1 14:19
     * @Param: [] 请求参数
     * @return: void 响应参数
     */
    @PostConstruct
    public void init()
    {
        Set<Map.Entry<String, Object>> beanSet = applicationContext.getBeansOfType(Object.class).entrySet();
        for (Map.Entry<String, Object> beanEntry : beanSet)
        {
            Object bean = beanEntry.getValue();
            if (null == bean)
            {
                continue;
            }

            // 获取真实对象
            try
            {
                bean = DynamicProxyUtil.getTarget(bean);
            } catch (Exception e)
            {
                logger.error("获取真实对象时失败: ", e);
            }
            Class<?> beanClass = bean.getClass();

            Field[] fields = beanClass.getDeclaredFields();
            for (Field field : fields)
            {
                RemoteRef remoteRef = field.getType().getAnnotation(RemoteRef.class);
                if (null == remoteRef)
                {
                    continue;
                }
                field.setAccessible(true);
                String beanName = Introspector.decapitalize(ClassUtils.getShortName(field.getType().getName()));

                Object object = null;
                try
                {
                    try
                    {
                        object = applicationContext.getBean(beanName);
                    } catch (Exception e)
                    {
                        object = applicationContext.getBean(field.getType());
                    }
                    bean = DynamicProxyUtil.getTarget(bean);
                    field.set(bean, object);
                } catch (Throwable e)
                {
                    throw new RuntimeException("init bean{beanName:" + beanName + ", beanClass:" + bean.getClass().getName() + "} cloud ref error: ", e);
                }
            }
        }
    }
}
