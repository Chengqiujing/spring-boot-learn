package com.chengqj.study.springbootkafka.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author chengqj
 * @Date 2020/11/11 14:12
 * @Desc
 */
public class UserDefinePartitioner implements Partitioner {
    private final AtomicInteger counter = new AtomicInteger(0);

    /**
     * 根据默认 DefaultPartitioner
     * 没有key的时候,默认是轮询到分区
     * 有key的时候,默认是hash到分区
     * 明确指定分区的时候,用指定的分区
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 获取所有分区
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int numPartitions = partitionInfos.size();
        if (key == null) {
            int andIncrement = counter.getAndIncrement();
            return (andIncrement & Integer.MAX_VALUE) % numPartitions;
        }

        // hash the keyBytes to choose a partition
        return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;

    }

    @Override // 生命周期函数
    public void close() {

    }

    @Override // 生命周期函数
    public void configure(Map<String, ?> map) {

    }
}
