package com.chengqj.study.springbootkafka.offset;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * @Author chengqj
 * @Date 2020/11/11 9:45
 * @Desc 手动提交偏移量
 */
public class KafkaConsumerOffsetDemo4 {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "MyCentOS:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "g101");

        //-------------Demo---------------
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // 配置offset自动提交的时间间隔 10s自动提交offset
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        // props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 10000);
        //-------------Demo---------------


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
                Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();

                while (recordIterator.hasNext()) {

                    // 获取一个消费消息
                    ConsumerRecord<String, String> record = recordIterator.next();
                    String topic = record.topic();
                    int partition = record.partition();
                    long offset = record.offset();

                    String key = record.key();
                    String value = record.value();
                    long timestamp = record.timestamp();

                    //-------------Demo---------------
                    // 注意offset要+1,将偏移量移动到要读取的位置,不然总是读取最后一条
                    offsets.put(new TopicPartition(topic, partition), new OffsetAndMetadata(offset + 1));
                    consumer.commitAsync(offsets, new OffsetCommitCallback() {
                        @Override
                        public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                            System.out.println("offsets:" + offsets + "\t" + "exception:" + exception);
                        }
                    });
                    //-------------Demo---------------

                    System.out.println(topic + "\t" + partition + "," + offset + "\t" + key + " " + value + " " + timestamp);
                }
            }
        }
    }
}
