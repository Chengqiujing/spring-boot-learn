package com.chengqj.study.swagger.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author chengqj
 * @Date 2020/11/1 16:30
 * @Desc
 */
@Data
public class RestEntity {
    @ApiModelProperty(value = "name", example = "我是rest")
    private String name;

    @ApiModelProperty(value = "age", example = "19")
    private Integer age;

}
