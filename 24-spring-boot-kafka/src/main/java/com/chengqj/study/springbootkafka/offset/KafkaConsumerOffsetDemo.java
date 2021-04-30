package com.chengqj.study.springbootkafka.offset;

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
 * @Desc earliest 偏移量Demo
 */
public class KafkaConsumerOffsetDemo {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "MyCentOS:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "g102");

        /**
         * 当kafka中没有偏移量offset时(未访问过kafka), 有三种策略 latest earliest none
         * 默认时消费 latest 第一次从消费者拉取开始计算,历史拿不到
         * earliest 第一次从最早开始
         * none 如果未找到消费者偏移量,则向消费者抛出异常
         */
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        // 订阅相关topic
        consumer.subscribe(Pattern.compile("^topic.*"));

        readRecords(consumer);
    }

    private static void readRecords(KafkaConsumer<String, String> consumer) {
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
