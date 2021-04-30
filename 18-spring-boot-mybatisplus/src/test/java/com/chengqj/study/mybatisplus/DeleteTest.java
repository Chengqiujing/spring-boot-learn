package com.chengqj.study.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chengqj.study.mybatisplus.test.dao.UserMapper;
import com.chengqj.study.mybatisplus.test.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author chengqiujing
 * @Date 2020/4/13 9:09
 * @Desc
 */
@SpringBootTest
public class DeleteTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void deleteById(){
        int i = userMapper.deleteById(123123);
        System.out.println(i);
    }




}
