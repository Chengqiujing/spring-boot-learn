package com.chengqj.study.springbootweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConfigInfoController {
    @Autowired
    ConfigInfo  configInfo;

    @RequestMapping("/selfdef2")
    @ResponseBody
    public String getDefination(){
        return configInfo.getAge() + ":" + configInfo.getName();
    }



}
