package com.chengqj.study.springbootpackage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public Map<String,String> hello(){
        HashMap<String, String> map = new HashMap<>();
        map.put("test","afdadsf");
        return map;
    }

}
