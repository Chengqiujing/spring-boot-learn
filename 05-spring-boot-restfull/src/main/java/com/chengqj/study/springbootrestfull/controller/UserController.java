package com.chengqj.study.springbootrestfull.controller;

import com.chengqj.study.springbootrestfull.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    //http://localhost:8080/boot/user/12/zhangsan
    @RequestMapping("boot/user/{id}/{name}")
    public User getUser(@PathVariable("id") Integer id,@PathVariable("name") String name){
        User user = new User();
        user.setAge(12);
        user.setName(name);
        user.setId(id);

        return user;
    }

    //@GetMapping

    //@PostMapping

    //@DeleteMapping

    //@PutMapping


}
