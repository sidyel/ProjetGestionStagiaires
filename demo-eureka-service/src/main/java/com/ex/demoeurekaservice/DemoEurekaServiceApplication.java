package com.ex.demoeurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DemoEurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoEurekaServiceApplication.class, args);
    }

}
