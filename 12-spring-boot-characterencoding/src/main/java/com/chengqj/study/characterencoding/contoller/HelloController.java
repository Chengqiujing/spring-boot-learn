package com.chengqj.study.characterencoding.contoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello 中文测试";//当核心配置文件中指定spring.http.encoding.enabled=false，测试会不会出现乱码
    }
}
