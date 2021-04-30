# Kafka 学习

### 环境准备

- CentOS
- [zookeeper安装包](https://zookeeper.apache.org/releases.html) 可选择3.6.2版本
- [kafka安装包](https://kafka.apache.org/downloads) 可选择2.11-2.2.0版本
- JDK1.8

### 安装步骤

1. 安装JDK,配置JAVA_HOME
2. 配置主机名和ip映射
3. 关闭防火墙&关闭防火墙开机自启动
4. 同步时钟 ntpdate cn.pool.ntp.org | ntp[1-7].aliyun.com
5. 安装&启动Zookeeper
6. 安装&启动|关闭Kafka

##### 1.安装JDK,配置JAVA_HOME

```shell
# 确认有没有安装jdk
rpm -qa|grep jdk 

# 安装jdk
rpm -ivh jdk=8u191-linux-x64.rpm
默认安装在/usr目录下

# 确认是否安装成功
#由于centos中默认识别java命令,所以可以用jps命令(同样是bin下的命令)确认
jps

# 配置环境变量
#这里需要注意Linux下可以配置环境变量的地方有4处,分别对应不同的时效和用户范围,这里我只介绍两种长久的
#1.单用户长久
~/.bash_profile
#2.全用户长久 
/etc/profile

# 在/etc/profile中
export JAVA_HOME=/usr/java/jdk1.8.0_271
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib

额外命令
# 卸载jdk
rpm -e `rpm -qa|grep jdk`
```

##### 2.配置主机名和ip映射

```shell
# 查看&修改
cat /etc/sysconfig/network

vim /etc/sysconfig/network
# Created by cloud-init on instance boot automatically, do not edit.
#
NETWORKING=yes
HOSTNAME=MyCentOS

reboot # 重启

# 配置主机名ip映射
ifconfig #获取ip

vim /etc/hosts 
1*.*.*.* MyCentOS

```

##### 3.关闭防火墙

```shell
# 查看防火墙状态
service iptables status

# 关闭
service iptables stop

# 从开机自启中剔除
chkconfig iptables off

额外命令
# 启动iptables
service iptables start
# 查看开机自启服务
chkconfig --list
```

##### 4.同步时钟

只有集群需要

```shell
ntpdate cn.pool.ntp.org | ntp[1-7].aliyun.com

# 安装
yum install ntp -y
# 校准
ntpdate ntp2.aliyun.com
# 写入计时电路
clock -w 
```

##### 5.安装zookeeper

```shell
# 解压到/usr下
tar -zxvf apache-zookeeper-3.6.2-bin.tar.gz -C /usr/

# 备份一份配置文件
cp conf/zoo_sample.cfg conf/zoo.cfg

# 修改配置文件
vim zoo_sample.cfg
dataDir=/data/zookeeper # 修改数据保存目录

# 启动zookeeper
./bin/zkServer.sh start ./zoo.cfg

# 查看
jps
18796 QuorumPeerMain
或者
./bin/zkServer.sh status
Mode: standalone

注意
我所用版本的zookeeper必须将配置文件放在conf目录下,不然会在启动或者查看状态时报没有文件或者目录的错误(找不到配置文件)
```

##### 6.安装kafka

```shell
# 解压
tar -zxvf kafka_2.11-2.2.0.tgz -C /usr/
# 配置文件修改
修改server.properties

如果时单点的话可以不该,集群就要改
broker.id=0
修改监听端口,注意使用主机名而不是IP
listeners=PLAINTEXT://MyCentOS:9092 
配置存储日志(注意启动kafka的角色有权限读写这个目录)
log.dirs=/data/kafka-logs
修改zookeeper的连接地址,注意是主机名
zookeeper.connect=MyCentOS:2181

# 启动
./bin/kafka-server-start.sh -daemon config/server.properties

# 查看启动情况
jps
# 关闭
./bin/kafka-server-stop.sh

```



### kafka使用

##### 创建topic

```shell
./kafka-topics.sh --bootstrap-server MyCentOS:9092 --create --topic topic01 --partitions 3 --replication-factor 1
#参数解释
--topic topic01 主题
--partitions 3 分区
--replication-factor 1 副本因子
```



##### 发送topic

```shell
./kafka-console-producer.sh --broker-list MyCentOS:9092 --topic topic01
```



##### 接受topic

```shell
./kafka-console-consumer.sh --bootstrap-server MyCentOS:9092 --topic topic01 --group group1
```

### kafka基础API

1. Topic的基本操作
2. 生产者
3. 消费者 sub/assign
4. 自定义分区
5. 序列化
6. 拦截器

##### Topic的基本操作

### kafka基础API高级特性

1. 偏移量(消费者角度)
2. ack/retry 应答机制/重试机制(生产者角度)
3. 幂等性和事务