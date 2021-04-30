package com.chengqj.study.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.chengqj.study.mybatisplus.test.dao.VideoMapper;
import com.chengqj.study.mybatisplus.test.entity.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private VideoMapper videoMapper;
    @Test
    void testMybatisPlus(){
        List<Video> videos = videoMapper.selectList(null);

        videos.forEach(System.out::println);
    }

    @Test
    void selectByWrapper(){
        QueryWrapper<Video> queryWrapper = new QueryWrapper<Video>();
        queryWrapper.like("name","海盗").gt("release_time","1997-01-01");
        videoMapper.selectList(queryWrapper);
    }
}
