package com.chengqj.study.springbootjackson.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author chengqj
 * @Date 2020/11/2 10:12
 * @Desc
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;
    private Integer age;

}
