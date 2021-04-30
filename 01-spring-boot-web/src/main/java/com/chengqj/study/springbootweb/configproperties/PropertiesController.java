package com.chengqj.study.springbootweb.configproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chengqj
 * @Date 2020/12/1 14:12
 * @Desc
 */
@RestController
// @EnableConfigurationProperties({ConfigByTestProperties.class}) // 如果配置类上没有@Component,则需指定
public class PropertiesController {

    @Autowired
    ConfigByTestProperties properties;

    @RequestMapping("/proper")
    public String getPropers(){
        System.out.println(properties);
        return "name: " + properties.getName() + "\n" + "age: " + properties.getAge() + "\n" +"address: " + properties.getAddress();
    }
}
