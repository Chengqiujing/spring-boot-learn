package com.chengqj.study.mybatisplus.test.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengqj.study.mybatisplus.test.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author chengqiujing
 * @Date 2020/4/12 20:02
 * @Desc
 */
public interface UserMapper extends BaseMapper<User> {

    //自定义sql 利用ibatis原生注解
    @Select("select * from user ${ew.customSqlSegment}")//也可以写在xml文件中，如果写在xml中则此处不需要注解
    public List<User> selectAll(@Param(Constants.WRAPPER)Wrapper<User> wrapper); //Constants.WRAPPER 即ew

    //自定义分页

    public Page<User> selectUserPage(Page<User> page,@Param(Constants.WRAPPER)Wrapper<User> wrapper);

    //自定义方法
    public int deleteAll();
}
