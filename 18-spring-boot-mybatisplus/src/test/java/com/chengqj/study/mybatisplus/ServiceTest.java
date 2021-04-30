package com.chengqj.study.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chengqj.study.mybatisplus.test.entity.User;
import com.chengqj.study.mybatisplus.test.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @Author chengqiujing
 * @Date 2020/4/12 20:08
 * @Desc
 */
@SpringBootTest
public class ServiceTest {
    @Autowired
    UserService userService;

    @Test
    void getOne(){
        User one = userService.getOne(Wrappers.<User>query().eq("age", 20));
        System.out.println(one);
    }

    @Test
    void batch(){
        User user = new User();
        user.setAge(21);
        user.setEmail("abc@163.com");
        user.setName("徐丽1");
        user.setId(321456987);

        User user1 = new User();
        user1.setAge(27);
        user1.setEmail("abc@163.com");
        user1.setName("徐丽2");
        user1.setId(321456952);

        List<User> list = Arrays.asList(user,user1);
        boolean b = userService.saveBatch(list);
        userService.saveOrUpdateBatch()
        System.out.println(b);
    }

}
