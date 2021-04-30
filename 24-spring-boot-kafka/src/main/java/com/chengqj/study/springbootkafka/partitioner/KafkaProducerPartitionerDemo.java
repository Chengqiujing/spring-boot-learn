package com.chengqj.study.springbootkafka.partitioner;

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
public class KafkaProducerPartitionerDemo {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "MyCentOS:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 用自定义的分区策略
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, UserDefinePartitioner.class);

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            // ProducerRecord record = new ProducerRecord<>("topic02", "key"+i, "value"+i);
            ProducerRecord record = new ProducerRecord<>("topic02", "value" + i);
            // 发送消息给服务器
            producer.send(record);
        }

        // 关闭生产者
        producer.close();
    }
}
