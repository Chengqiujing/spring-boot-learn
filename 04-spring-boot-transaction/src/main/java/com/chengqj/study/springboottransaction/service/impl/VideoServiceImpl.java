package com.chengqj.study.springboottransaction.service.impl;

import com.chengqj.study.springboottransaction.dao.VideoDao;
import com.chengqj.study.springboottransaction.entity.VideoEntity;
import com.chengqj.study.springboottransaction.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoDao videoDao;

    //测试事务
    @Override
    @Transactional
    public boolean addVideo(VideoEntity videoEntity) {

        Integer integer = videoDao.insertOne(videoEntity);
        int a = 10/0;
        return integer > 0;
    }

    @Override
    public boolean delVideo(Integer id) {
        return false;
    }

    @Override
    public boolean updateVideo(VideoEntity videoEntity) {
        return false;
    }

    @Override
    public VideoEntity selectVideo(Integer id) {
        return null;
    }
}
