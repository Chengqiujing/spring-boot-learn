package com.chengqj.study.dubboconsumer.contoller;

import api.Hello;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Consumer {
    @Reference(version = "${demo.service.version}")
    private Hello helloService;


    //熔断器指定 2 秒 10 试错，默认是 5s - 20次
    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/hi")
    @ResponseBody
    public String sayHello() throws InterruptedException {
        return helloService.sayHello();
    }

    @ResponseBody
    public String fallback(){
        return "Hystrix break......";
    }


}
