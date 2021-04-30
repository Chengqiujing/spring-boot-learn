package com.chengqj.study.springbootjackson.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author chengqj
 * @Date 2020/11/2 10:39
 * @Desc
 */
@Data
@Builder
@JsonPropertyOrder(value = {"address", "name"}) // 指定返回json顺序
public class Student {

    @JsonProperty("sduname") // 为字段重新指定名字
    private String name;

    @JsonIgnore // 忽略一个字段
    private Integer age;

    @JsonInclude(JsonInclude.Include.NON_NULL) // 指定为空的时候不要返回
    private String address;

    // 自定义配置时间格式使用@JsonFormat
    @JsonFormat(pattern = "yyyy_MM_dd HH:mm:ss", timezone = "GMT+8")
    private Date birthDate;
}
