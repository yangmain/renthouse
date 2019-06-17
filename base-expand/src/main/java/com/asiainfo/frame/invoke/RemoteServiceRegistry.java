package com.asiainfo.frame.invoke;

import com.asiainfo.annotations.RemoteInfc;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.beans.Introspector;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Ares
 * @Date: 2019/6/1 15:58
 * @Description: 手动生成bean并注册到spring容器
 * @Version: JDK 1.8
 */
@Configuration
public class RemoteServiceRegistry implements BeanDefinitionRegistryPostProcessor
{
    private static final Logger logger = LoggerFactory.getLogger(RemoteServiceRegistry.class);

    /**
     * 配置文件中的服务列表
     */
    private static List<RemoteProxyService> SERVICE_LIST = null;

    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();

    private static final String JSON_FILE_NAME = "remote-service.json";

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException
    {
        URL url = ClassLoader.getSystemResource(JSON_FILE_NAME);
        if (null == url)
        {
            logger.info("远程调用配置文件[{}]不存在,不再生成代理服务并注册", JSON_FILE_NAME);
            return;
        }
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, RemoteProxyService.class);
            SERVICE_LIST = mapper.readValue(new File(url.getPath()), javaType);
        } catch (IOException e)
        {
            logger.error("读取远程调用配置文件[{}]并转为远程代理服务时出错: ", JSON_FILE_NAME, e);
        }

        List<String> filterList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(SERVICE_LIST))
        {
            for (RemoteProxyService service : SERVICE_LIST)
            {
                String beanUnique = service.getServiceCenter() + ":" + service.getRemoteInfcName();
                if (filterList.contains(beanUnique))
                {
                    // 如果该bean已经加载过则不再加载
                    continue;
                }
                logger.info("开始生成远程代理服务并注册,服务bean: {}", beanUnique);
                Class<?> clazz;
                try
                {
                    clazz = Class.forName(service.getRemoteInfcName());
                    RemoteInfc remoteInfc = clazz.getAnnotation(RemoteInfc.class);
                    if (null == remoteInfc)
                    {
                        logger.error("当前服务不是远程接口,请为{}加上RemoteInfc注解", service.getRemoteInfcName());
                        continue;
                    }
                } catch (ClassNotFoundException e)
                {
                    logger.error("加载class时失败,请检查远程调用配置文件[{}]: ", JSON_FILE_NAME, e);
                    continue;
                }

                AnnotatedBeanDefinition annotatedBeanDefinition = new AnnotatedGenericBeanDefinition(RemoteServiceFactory.class);

                ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(annotatedBeanDefinition);
                annotatedBeanDefinition.setScope(scopeMetadata.getScopeName());

                annotatedBeanDefinition.setAutowireCandidate(true);
                annotatedBeanDefinition.getPropertyValues().addPropertyValue("remoteInfcClass", clazz);
                annotatedBeanDefinition.getPropertyValues().addPropertyValue("serviceCenter", service.getServiceCenter());

                String beanName = Introspector.decapitalize(ClassUtils.getShortName(service.getRemoteInfcName()));
                BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(annotatedBeanDefinition, beanName, new String[]{service.getServiceId()});
                BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, beanDefinitionRegistry);
                filterList.add(beanUnique);
                logger.info("生成远程代理服务并注册成功,服务bean: {}", beanUnique);
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException
    {

    }
}
