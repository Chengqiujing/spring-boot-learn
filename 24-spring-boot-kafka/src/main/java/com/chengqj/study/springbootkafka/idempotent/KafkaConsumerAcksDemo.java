package com.chengqj.study.springbootkafka.idempotent;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * @Author chengqj
 * @Date 2020/11/11 9:45
 * @Desc
 */
public class KafkaConsumerAcksDemo {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "MyCentOS:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "g1");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        // 订阅相关topic
        consumer.subscribe(Pattern.compile("^topic.*"));

        // 遍历消息队列
        while (true) {
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(1));
            if (!poll.isEmpty()) { // 如果有数据
                Iterator<ConsumerRecord<String, String>> recordIterator = poll.iterator();
                while (recordIterator.hasNext()) {
                    // 获取一个消费消息
                    ConsumerRecord<String, String> record = recordIterator.next();
                    String topic = record.topic();
                    int partition = record.partition();
                    long offset = record.offset();

                    String key = record.key();
                    String value = record.value();
                    long timestamp = record.timestamp();

                    System.out.println(topic + "\t" + partition + "," + offset + "\t" + key + " " + value + " " + timestamp);
                }
            }
        }
    }


}
