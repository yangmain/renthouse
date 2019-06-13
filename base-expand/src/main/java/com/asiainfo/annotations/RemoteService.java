package com.asiainfo.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Ares
 * @Date: 2019/6/1 16:00
 * @Description: 远程服务实现注解
 * @Version: JDK 1.8
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RemoteService
{
    String version() default "1.0.0";
}
