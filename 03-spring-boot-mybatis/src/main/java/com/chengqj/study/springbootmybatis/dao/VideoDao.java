package com.chengqj.study.springbootmybatis.dao;

import com.chengqj.study.springbootmybatis.entity.VideoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface VideoDao {
    public VideoEntity select(Integer id);

    public Integer insertOne(VideoEntity v);

    public Integer delete(Integer id);

    public Integer update(VideoEntity v);

}
