package com.chengqj.study.springbootkafka.dml;

import org.apache.kafka.clients.admin.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @Author chengqj
 * @Date 2020/11/10 17:37
 * @Desc
 */

// 必须将CentOSA,B,C这三个主机的IP和主机名映射配置到操作系统中
public class KafkaTopicDML {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1. 创建KafkaAdminClient
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "MyCentOS:9092");

        KafkaAdminClient adminClient = (KafkaAdminClient) KafkaAdminClient.create(props);

        // 创建Topic(异步)
        // CreateTopicsResult createTopicsResult = adminClient.createTopics(Arrays.asList(new NewTopic("topic02", 3, (short) 1)));
        // createTopicsResult.all().get(); // 添加此句变为同步

        // 查看topic列表
        ListTopicsResult topicsResult = adminClient.listTopics();
        Set<String> names = topicsResult.names().get();
        for (String name : names) {
            System.out.println(name);
        }

        // 删除Topic(异步)
        // DeleteTopicsResult deleteTopics = adminClient.deleteTopics(Arrays.asList("topic01"));
        // deleteTopics.all().get(); // 添加此句变为同步

        // 查看topic详细信息
        DescribeTopicsResult describeTopics = adminClient.describeTopics(Arrays.asList("topic02"));
        Map<String, TopicDescription> stringTopicDescriptionMap = describeTopics.all().get();
        for (Map.Entry<String, TopicDescription> stringTopicDescriptionEntry : stringTopicDescriptionMap.entrySet()) {
            System.out.println(stringTopicDescriptionEntry.getKey() + "\t" + stringTopicDescriptionEntry.getValue());
        }
        // 关闭AdminClient
        adminClient.close();
    }

}
