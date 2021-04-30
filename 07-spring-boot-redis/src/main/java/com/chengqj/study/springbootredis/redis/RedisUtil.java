package com.chengqj.study.springbootredis.redis;

import com.chengqj.study.springbootredis.util.SpringUtil;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


public class RedisUtil {

    /**
     * 获取bean方式获取RedisTemplate
     *
     *  这是因为这个类是一个工具类，不想把它注册为spring的bean，则无法启用spring注入的方式获取RedisTemplate
     */

    static RedisTemplate<String,String> redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);

    /**
     * 设置String
     * @param key
     * @param value
     */
    public static void setString(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 切换Redis数据库(试验不成功)
     * @param db
     * @param key
     * @param value
     */
    public static void chooseDBAndSet(int db, String key,String value){
        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();

        if(connectionFactory != null){
            connectionFactory.setDatabase(db);
            //connectionFactory.setShareNativeConnection(false); //不启用redis连接缓存
            redisTemplate.setConnectionFactory(connectionFactory);
            connectionFactory.resetConnection(); //重置连接

            redisTemplate.opsForValue().set(key,value);
        }
    }

    /**
     * 防止缓存穿透 -- 双重检测（类似 单例）
     *
     * 这种方法 防止单体架构时的访问
     */
    public static String getStringDataThroughDB(String key){
        String value = redisTemplate.opsForValue().get(key);

        if(value == null){ // 第一层检测
            synchronized(RedisUtil.class){
                value = redisTemplate.opsForValue().get(key);
                if(value == null){ // 第二层检测
                    //TODO 数据库获取数据 并返回
                    System.out.println("查询数据库");
                    RedisUtil.setString("doubleCheck","123");
                    return "123";
                }
                System.out.println("二重检验-查询缓存");
            }
        }
        System.out.println("直接-查询缓存");
        return value;
    }


    /**
     *  分布式锁
     */


    /**
     * 基于分布式锁的 双重检测
     */
}
