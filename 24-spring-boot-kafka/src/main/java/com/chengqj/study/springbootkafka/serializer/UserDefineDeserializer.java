package com.chengqj.study.springbootkafka.serializer;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @Author chengqj
 * @Date 2020/11/11 14:38
 * @Desc
 */
public class UserDefineDeserializer implements Deserializer<Object> {
    @Override
    public Object deserialize(String topic, byte[] data) {
        // 用commons-lang3实现
        return SerializationUtils.deserialize(data);
        // return null;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        System.out.println("configure");
    }

    @Override
    public Object deserialize(String topic, Headers headers, byte[] data) {
        System.out.println("deserialize--heard");
        // 用commons-lang3实现
        return SerializationUtils.deserialize(data);
        // return null;
    }

    @Override
    public void close() {
        System.out.println("close");
    }
}
