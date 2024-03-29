package com.asiainfo.strategy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.asiainfo.frame", "com.asiainfo.strategy"})
@EnableEurekaClient
public class SharingStrategyImplApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SharingStrategyImplApplication.class, args);
    }

}
