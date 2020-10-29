package com.scg.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfigProviderDeptClient8001 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigProviderDeptClient8001.class, args);
    }

}
