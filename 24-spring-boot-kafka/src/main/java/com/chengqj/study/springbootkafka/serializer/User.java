package com.chengqj.study.springbootkafka.serializer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author chengqj
 * @Date 2020/11/11 14:47
 * @Desc
 */
@Data
@AllArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String name;
    private Date birthday;
}
