# spring-boot-redis

### 引入依赖

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
```

### application.properties

```
spring.redis.host=127.0.0.1
spring.redis.port=6379
#spring.redis.password=root
#spring.redis.database=4
```

### 获取 RedisTemplate 的集中方式

1. 使用spring boot的注入

```
@Autowire
RedisTemplate<Object,Object> redisTemplate;
或者
@Autowire
RedisTemplate<String,String> redisTemplate;

```
**PS:**在Spring Boot下就只有这两个泛型实例可以注入。

2. 工厂方式注入


3. 自定义注入



### Redis操作

```
指定序列化器

//序列化器
        RedisSerializer keySerializer = new StringRedisSerializer();
        RedisSerializer valSerializer = new GenericJackson2JsonRedisSerializer();
 尽量使用带2的

redisTemplate.opsForValue().set(key,value); 
redisTemplate.opsForSet()
...

```

## 问题

1. redis 临时更换数据库  测试失败

2. redis分布式锁 未研究