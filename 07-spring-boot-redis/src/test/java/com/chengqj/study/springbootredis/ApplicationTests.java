package com.chengqj.study.springbootredis;

import com.chengqj.study.springbootredis.redis.RedisUtil;
import com.chengqj.study.springbootredis.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    //测试插入String
    @Test
    void redisTest(){
        RedisUtil.setString("db1","testRedis");
    }

    //测试更改 redis库 查询
    @Test
    void redisDBChangeTest(){
        RedisUtil.chooseDBAndSet(5,"db5","test change redisDB");
    }

    //测试 单体架构下 双重检测
    @Test
    void redisDoubleCheckBySingleton(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                RedisUtil.getStringDataThroughDB("doubleCheck");
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 1000; i++) {
            System.out.println("执行第" + i + "个线程" );
            executorService.submit(runnable);
        }

        while(true);
    }

    //测试 单体架构下 双重检测
    @Test
    void redisDoubleCheckByFenBuShi(){

    }


    @Autowired
    RedisService redisService;
    @Test
    void setStringService(){

        redisService.setString("wan","wan");
    }

}
