package com.chengqj.study.springbootkafka.idempotent;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Author chengqj
 * @Date 2020/11/11 18:59
 * @Desc 因为有重试机制, 所以会出现重复数据
 * 幂等性要求服务端有去重的能力
 * 一般去重有两点:
 * 唯一标识 要想区分请求是否重复,请求中就得有唯一标识. 例如:订单号
 * 识别记录 记录已经请求的,进行识别. 这里不要用搜寻比对的方式,要用类似token一样的算法机制
 * 象如kafka中就是,用sequence和时间戳来代表唯一标识,当到来的时间戳在上一时间之前,就是重复
 * <p>
 * Kafka处理幂等通过exactly once(精准一次,流处理中常用)
 * 1.Kafka给生产者生成唯一ID成为Producer ID或称PID
 * 2.同时生成一个单调递增的序列号,将PID和序列号捆绑发送给Broker
 * 3.Broker仅当消息的序列号比该PID/TopicPartition对中最后提交的消息正好大于1时,才会接收.
 * <p>
 * enable.idempotence=false默认
 * 注意:在使用幂等性的时候,要求必须开启retries=true和acks=all
 * 还可设置props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
 * 保证顺序性
 */
public class KafkaIdempotenceDemo {
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

        // 开启Kafka的幂等性
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        // 这个配置属性表示,当有对应数量的请求没有回应时,则会阻塞客户端.如果大于1,则顺序会乱.要严格保证顺序则要设成1
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
        ProducerRecord record = new ProducerRecord<>("topic02", "ack", "test idempotent");
        // 发送消息给服务器
        producer.send(record);
        producer.flush(); //刷出本地缓存

        // 关闭生产者
        producer.close();
    }
}
