package com.chengqj.study.springbootfilter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/filter")
    public String hello(){
        return "hello filter";
    }
}
