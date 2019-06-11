package com.asiainfo.base;

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
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.beans.Introspector;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Ares
 * @Date: 2019/6/1 15:58
 * @Description: 手动生成bean并注册到spring容器
 * @Version: JDK 1.8
 */
@Configuration
public class RemoteServiceRegistry implements BeanDefinitionRegistryPostProcessor, ResourceLoaderAware
{
    private static final Logger logger = LoggerFactory.getLogger(RemoteServiceRegistry.class);

    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();

    private static final String JSON_FILE_NAME = "classpath:remote-service.json";

    private ResourceLoader resourceLoader;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException
    {
        List<RemoteProxyService> serviceList = null;
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, RemoteProxyService.class);
            serviceList = mapper.readValue(resourceLoader.getResource(JSON_FILE_NAME).getInputStream(), javaType);
        } catch (IOException e)
        {
            logger.error("读取文件并转为远程代理服务时出错: ", e);
        }

        if (!CollectionUtils.isEmpty(serviceList))
        {
            for (RemoteProxyService proxyService : serviceList)
            {
                logger.info("开始生成远程代理服务,服务id: {}", proxyService.getServiceId());
                Class<?> clazz;
                Class<?> requestType;
                Method method;
                try
                {
                    clazz = Class.forName(proxyService.getServiceName());
                    requestType = Class.forName(proxyService.getRequestType());
                    method = clazz.getMethod(proxyService.getMethodName(), requestType);
                } catch (ClassNotFoundException e)
                {
                    logger.error("加载class时失败,请检查json配置: ", e);
                    continue;
                } catch (NoSuchMethodException e)
                {
                    logger.error("获取method时失败,请检查json配置: ", e);
                    continue;
                }

                AnnotatedBeanDefinition annotatedBeanDefinition = new AnnotatedGenericBeanDefinition(RemoteServiceFactory.class);

                ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(annotatedBeanDefinition);
                annotatedBeanDefinition.setScope(scopeMetadata.getScopeName());

                annotatedBeanDefinition.setAutowireCandidate(true);
                proxyService.setProxyMethod(method);
                annotatedBeanDefinition.getPropertyValues().addPropertyValue("remoteService", clazz);
                annotatedBeanDefinition.getPropertyValues().addPropertyValue("serviceCenter", proxyService.getServiceCenter());

                String beanName = Introspector.decapitalize(ClassUtils.getShortName(proxyService.getServiceName()));
                BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(annotatedBeanDefinition, beanName, new String[]{proxyService.getServiceId()});
                BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, beanDefinitionRegistry);
                logger.info("生成远程代理服务结束,服务id: {}", proxyService.getServiceId());
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException
    {

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader)
    {
        this.resourceLoader = resourceLoader;
    }
}
