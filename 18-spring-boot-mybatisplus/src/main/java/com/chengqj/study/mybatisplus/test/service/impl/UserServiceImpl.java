package com.chengqj.study.mybatisplus.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengqj.study.mybatisplus.test.dao.UserMapper;
import com.chengqj.study.mybatisplus.test.entity.User;
import com.chengqj.study.mybatisplus.test.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author chengqiujing
 * @Date 2020/4/12 20:06
 * @Desc
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
