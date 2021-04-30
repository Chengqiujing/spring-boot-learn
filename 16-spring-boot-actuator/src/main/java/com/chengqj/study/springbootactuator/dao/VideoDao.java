package com.chengqj.study.springbootactuator.dao;


import com.chengqj.study.springbootactuator.entity.VideoEntity;

//@Mapper
public interface VideoDao {
    public VideoEntity select(Integer id);

    public Integer insertOne(VideoEntity v);

    public Integer delete(Integer id);

    public Integer update(VideoEntity v);

}
