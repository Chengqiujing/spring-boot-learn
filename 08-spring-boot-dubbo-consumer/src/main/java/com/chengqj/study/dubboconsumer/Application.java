package com.chengqj.study.dubboconsumer;

import api.Hello;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @Reference(version = "${demo.service.version}")
//    private Hello helloService;
//    @Bean
//    public ApplicationRunner runner() {
//        return args -> logger.info(helloService.sayHello());
//    }
}
