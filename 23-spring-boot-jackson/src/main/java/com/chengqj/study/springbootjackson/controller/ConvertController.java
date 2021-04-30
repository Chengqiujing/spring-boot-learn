package com.chengqj.study.springbootjackson.controller;

import com.chengqj.study.springbootjackson.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author chengqj
 * @Date 2020/11/2 9:14
 * @Desc
 */
@Controller
@RequestMapping("/convert")
public class ConvertController {

    @RequestMapping("/users")
    @ResponseBody
    public User get() {
        /**
         * 对@ResponseBody的序列化在ResponseToXlsConverter进行了重写
         * 会输出为一个excel文件
         */

        return User.builder().age(19).name("zhangsan").build();
    }


}
