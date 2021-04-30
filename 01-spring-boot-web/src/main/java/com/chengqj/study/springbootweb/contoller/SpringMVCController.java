package com.chengqj.study.springbootweb.contoller;

import org.springframework.web.bind.annotation.*;

@RestController
public class SpringMVCController {
    @GetMapping("/get")
    public String get(){
        return "get request";
    }

    @PostMapping("/post")
    public String post(){
        return "post request";
    }

    //@PutMapping


    //@DeleteMapping
}
