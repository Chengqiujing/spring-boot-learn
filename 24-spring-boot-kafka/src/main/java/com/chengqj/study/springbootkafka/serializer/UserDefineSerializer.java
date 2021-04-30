package com.chengqj.study.springbootkafka.serializer;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author chengqj
 * @Date 2020/11/11 14:38
 * @Desc
 */
public class UserDefineSerializer implements Serializer<Object> {
    @Override // 生命周期函数
    public void configure(Map<String, ?> configs, boolean isKey) {
        System.out.println("configure");
    }

    @Override
    public byte[] serialize(String topic, Object data) {
        // 用commons-lang3实现
        return SerializationUtils.serialize((Serializable) data);
        // return new byte[0];
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Object data) {
        // 用commons-lang3实现
        return SerializationUtils.serialize((Serializable) data);
        // return new byte[0];
    }

    @Override // 生命周期函数
    public void close() {
        System.out.println("close");
    }
}
