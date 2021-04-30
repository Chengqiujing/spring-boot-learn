package com.chengqj.study.springbootjavaobject.service.impl;

import com.chengqj.study.springbootjavaobject.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("say hello");
    }
}
