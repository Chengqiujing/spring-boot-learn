package com.chengqj.study.springbootjavaobject;

import com.chengqj.study.springbootjavaobject.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {
    /**
     * 方式一：使用spring boot 容器对象ConfigurableApplicationContext
     */
//    public static void main(String[] args) {
//        //利用容器对现象获取 bean
//        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
//        HelloService helloServiceImpl = (HelloService) run.getBean("helloServiceImpl");
//        helloServiceImpl.sayHello();
//    }

    /**
     * 方式二：实现 CommandLineRunner 接口
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    HelloService helloService;
    // 类似纯java 入口main函数
    @Override
    public void run(String... args) throws Exception {
        helloService.sayHello();
    }
}
