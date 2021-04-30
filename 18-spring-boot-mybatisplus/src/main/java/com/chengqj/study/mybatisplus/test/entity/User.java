package com.chengqj.study.mybatisplus.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @Author chengqiujing
 * @Date 2020/4/12 20:00
 * @Desc
 */

@Data
public class User extends Model<User> {

    @TableId(type=IdType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private Integer age;

}
