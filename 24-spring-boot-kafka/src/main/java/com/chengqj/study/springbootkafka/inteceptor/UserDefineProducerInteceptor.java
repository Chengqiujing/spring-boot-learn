package com.chengqj.study.springbootkafka.inteceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Author chengqj
 * @Date 2020/11/11 15:05
 * @Desc
 */
public class UserDefineProducerInteceptor implements ProducerInterceptor {
    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        // 拦截 + 修饰
        return new ProducerRecord(record.topic(), record.key(), record.value() + "-- mashibing.com");
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        // 成功或失败时 通知
        System.out.println("metadata:" + metadata + "exception:" + exception);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {


    }
}
