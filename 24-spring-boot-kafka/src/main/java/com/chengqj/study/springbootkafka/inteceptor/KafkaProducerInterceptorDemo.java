package com.chengqj.study.springbootkafka.inteceptor;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Author chengqj
 * @Date 2020/11/11 9:45
 * @Desc
 */
public class KafkaProducerInterceptorDemo {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "MyCentOS:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 加入自定义的拦截器
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, UserDefineProducerInteceptor.class.getName());

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10; i++) {
            ProducerRecord record = new ProducerRecord<>("topic02", "value" + i);
            // 发送消息给服务器
            producer.send(record);
        }

        // 关闭生产者
        producer.close();
    }
}
