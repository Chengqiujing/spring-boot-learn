package com.chengqj.study.springbootkafka.serializer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Date;
import java.util.Properties;

/**
 * @Author chengqj
 * @Date 2020/11/11 9:45
 * @Desc
 */
public class KafkaProducerSerializerDemo {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "MyCentOS:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UserDefineSerializer.class.getName()); // 指定序列化
        KafkaProducer<Integer, User> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {

            ProducerRecord record = new ProducerRecord<>("topic02", new User(i, "n" + i, new Date()));
            // 发送消息给服务器
            producer.send(record);
        }

        // 关闭生产者
        producer.close();
    }
}
