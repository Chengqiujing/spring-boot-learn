package com.chengqj.study.dubboprovider.service;

import api.Hello;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

@Service(version = "${demo.service.version}")
public class Provider implements Hello {

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${dubbo.protocol.port}")
    private String port;

    private int count = 0;

    //熔断器指定 2 秒 10 试错，默认是 5s - 20次
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value="3"),  //
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="2000") //超时时间
    })
    @Override
    public String sayHello() throws InterruptedException {
        String str = String.format("[%s] : Hello, %s", serviceName, "provider:"+port);

        System.out.println(count);

//        if(count++ < 2){
//            Thread.sleep(5000);
//        }
        if(count++ < 2) {
            throw new RuntimeException("test Hystrix");//Test Hystrix  Exception  break
        }
        return count + "";
    }

}
