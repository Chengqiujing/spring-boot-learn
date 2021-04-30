package com.chengqj.study.springbootkafka.ackretry;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Author chengqj
 * @Date 2020/11/11 18:28
 * @Desc
 */
public class KafkaProducerAckRetryDemo {
    /**
     * Kafka在发送玩一个消息后,要求Broker在规定的时间内Ack应答,否则Kafka生产者会尝试n次重新发送消息
     * acks=1 默认
     * acks=1
     * 这是默认的,表示Broker中的Leader接受成功就好,不会等Follower复制成功
     * 若在Follower复制前,Leader挂掉,则记录会消失
     * acks=0
     * 生产者根本不会等待服务器的任何确认.这中情况下不能保证消息已被记录.可用于效率高,但不重要的记录,例如日志
     * acks=all
     * 表示Leader将等待全套同步副本确认计录.这保证了只要至少一个同步符本仍处于活动状态,记录就不会丢失.
     * 这是最有力的保证,等效于acks = -1设置
     * <p>
     * 若Kafka收不到应答,可开启重试机制
     * request.timeout.ms=30000 默认
     * retries=2147483647 默认
     * <p>
     * 问题:
     * 这会引起重复数据,破坏幂等性
     */

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "MyCentOS:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 设置Kafka的 Acks以及retries
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 3); // 不包含第一次,如果尝试3次失败,则放弃
        // 将超时时间设为1ms用以造成超时重试现象
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 100);


        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
        ProducerRecord record = new ProducerRecord<>("topic02", "ack", "test ack");
        // 发送消息给服务器
        producer.send(record);
        producer.flush(); //刷出本地缓存

        // 关闭生产者
        producer.close();
    }
}
