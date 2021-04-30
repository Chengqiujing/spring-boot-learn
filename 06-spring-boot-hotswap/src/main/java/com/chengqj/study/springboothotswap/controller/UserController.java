package com.chengqj.study.springboothotswap.controller;

import com.chengqj.study.springboothotswap.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @RequestMapping("users")
    public List<User> getAll() {
        User user = new User();
        user.setAge(12);
        user.setId(1);
        user.setName("zhaosi12");

        List<User> list = new ArrayList<>();
        list.add(user);


        User user1 = new User();
        return list;
    }
}
