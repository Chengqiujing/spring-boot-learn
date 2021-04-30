package com.chengqj.study.springbootmybatis;

import com.chengqj.study.springbootmybatis.dao.VideoDao;
import com.chengqj.study.springbootmybatis.entity.VideoEntity;
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
    VideoDao videoDao;
    @Test
    void select (){
        System.out.println(videoDao.select(1));
    }
    @Test
    void insert() throws ParseException {
        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setName("加勒比海盗2");
        videoEntity.setSize("1.2G");
        videoEntity.setDescription("加勒比海盗三部曲 第二部");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse("2001-3-1");
        videoEntity.setReleaseDate(new java.sql.Date(parse.getTime()));
        videoEntity.setUptime(new java.sql.Date(new Date().getTime()));

        videoDao.insertOne(videoEntity);
    }
}
