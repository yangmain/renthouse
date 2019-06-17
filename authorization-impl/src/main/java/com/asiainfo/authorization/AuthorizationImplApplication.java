package com.asiainfo.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuthorizationImplApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(AuthorizationImplApplication.class, args);
    }

}
