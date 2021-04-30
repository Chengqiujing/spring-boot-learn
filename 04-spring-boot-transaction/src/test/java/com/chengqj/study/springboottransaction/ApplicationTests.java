package com.chengqj.study.springboottransaction;

import com.chengqj.study.springboottransaction.entity.VideoEntity;
import com.chengqj.study.springboottransaction.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    VideoService videoService;

    @Test
    void testTransaction() throws ParseException {
        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setName("加勒比海盗三");
        videoEntity.setSize("2G");
        videoEntity.setTime("2小时");
        videoEntity.setReleaseDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("2010-1-1").getTime()));
        videoEntity.setUptime(new java.sql.Timestamp(new Date().getTime()));

        videoService.addVideo(videoEntity);
    }
}
