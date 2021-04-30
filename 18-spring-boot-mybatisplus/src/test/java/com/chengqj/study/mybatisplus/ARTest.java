package com.chengqj.study.mybatisplus;

import com.chengqj.study.mybatisplus.test.dao.UserMapper;
import com.chengqj.study.mybatisplus.test.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author chengqiujing
 * @Date 2020/4/12 20:08
 * @Desc
 */
@SpringBootTest
public class ARTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void artest(){
        User user = new User();
        //user.setId(123);
        user.setAge(20);
        user.setEmail("liuming2@163.com ");
        user.setName("liuming2");
        boolean insert = user.insert();
        System.out.println(insert);
    }

    @Test
    void artest1(){
        User user = new User();
        user.setId(123);
        //User u = user.selectById();
        //boolean b = user.deleteById();
        boolean b = user.updateById();
        System.out.println(b);
    }
}
