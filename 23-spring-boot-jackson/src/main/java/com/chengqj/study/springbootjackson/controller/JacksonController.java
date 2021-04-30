package com.chengqj.study.springbootjackson.controller;

import com.chengqj.study.springbootjackson.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author chengqj
 * @Date 2020/11/2 10:42
 * @Desc 了解jackson的相关配置
 */
@Controller
@RequestMapping("/jackson")
@Slf4j
public class JacksonController {

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    @ResponseBody
    public Student get(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        log.info("传入时间为:" + date);

        return Student.builder()
                .name("今天生")
                .age(1)
                .birthDate(new Date(2020, 11, 2))
                .address("北京石景山")
                .build();
    }
}
