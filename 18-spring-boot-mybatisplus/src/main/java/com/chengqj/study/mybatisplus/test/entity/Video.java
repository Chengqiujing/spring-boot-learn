package com.chengqj.study.mybatisplus.test.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
/**
 * @Author chengqiujing
 * @Date 2020/4/10 12:00
 * @Desc
 */
@Data
@TableName("t_video")
public class Video {
    @TableId
    private Long id;
    @TableField("name")
    private String nname;

    private String time;

    private String size;

    private String description;

    private LocalDate releaseDate;

    private transient String remark;

}
