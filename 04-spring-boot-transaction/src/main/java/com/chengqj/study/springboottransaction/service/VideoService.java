package com.chengqj.study.springboottransaction.service;

import com.chengqj.study.springboottransaction.entity.VideoEntity;

public interface VideoService {

    public boolean addVideo(VideoEntity videoEntity);

    public boolean delVideo(Integer id);

    public boolean updateVideo(VideoEntity videoEntity);

    public VideoEntity selectVideo(Integer id);

}
