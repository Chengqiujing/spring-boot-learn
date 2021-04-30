package com.chengqj.study.springbootweb.contoller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SelfConfigurationContoller {
    @Value("${selfdefination.name}")
    private String name;
    @Value("${selfdefination.age}")
    private String age;

    @RequestMapping("/selfdef")
    @ResponseBody
    public String getSelfDefination(){
        return name + ":" + age;
    }
}
