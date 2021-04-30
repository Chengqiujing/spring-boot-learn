package com.chengqj.study.springboottransaction.dao;

import com.chengqj.study.springboottransaction.entity.VideoEntity;


public interface VideoDao {
    public VideoEntity select(Integer id);

    public Integer insertOne(VideoEntity v);

    public Integer delete(Integer id);

    public Integer update(VideoEntity v);

}
