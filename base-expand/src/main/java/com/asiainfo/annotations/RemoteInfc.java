package com.asiainfo.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Ares
 * @Date: 2019/6/1 16:01
 * @Description: 远程服务接口注解
 * @Version: JDK 1.8
 */

@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RemoteInfc
{
    String version() default "1.0.0";
}
