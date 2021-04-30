package com.chengqj.study.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengqj.study.mybatisplus.test.dao.UserMapper;
import com.chengqj.study.mybatisplus.test.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chengqiujing
 * @Date 2020/4/13 9:09
 * @Desc
 */
@SpringBootTest
public class UpdateTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void updateById(){
        User user = new User();
        user.setId(123123);
        user.setName("wangwu");
        int i = userMapper.updateById(user);
        System.out.println(i);

    }
    @Test
    void updateByWrapper(){
        //set
        User user = new User();
        user.setAge(20);
        //where
        UpdateWrapper<User> update = Wrappers.<User>update();
        update.eq("name","liyiwei");
        int i = userMapper.update(user,update);
        System.out.println(i);

    }



}
