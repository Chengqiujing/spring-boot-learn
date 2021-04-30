# spring-boot-actuator

通过Actuator监控程序装状态

pom

```xml
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

properties

```properties
# 指定Actuator对外暴露端口,不要和应用接口重复
management.server.port=9001
# 暴露所有节点
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
# 通过程序关闭应用
management.endpoint.shutdown.enabled=true
```

Actuator提供了13个API接口查看状态

基本路径base path = /actuator

| 类型 |     API端口     |                        描述                         |
| :--: | :-------------: | :-------------------------------------------------: |
| GET  |   /autoconfig   |                    自动配置报告                     |
| GET  |  /configprops   |              描述配置属性如何注入Bean               |
| GET  |     /beans      |                   展示全部的bean                    |
| GET  |   /threaddump   |                    线程活动快照                     |
| GET  |      /env       |                  获取全部环境属性                   |
| GET  |    /sessions    |                      用户会话                       |
| GET  |     /health     |                 应用程序的健康指标                  |
| GET  |      /info      |                    应用程序信息                     |
| GET  |  /auditevents   |             显示当前应用的审计事件信息              |
| GET  |   /conditions   | 显示配置类和自动配置类的状态,以及应用或被应用的元婴 |
| GET  |     /flyway     |                 显示数据库迁移路径                  |
| GET  |   /liquibase    |      展示任何Liquibase数据库迁移路径(如果存在)      |
| GET  |    /loggers     |               显示和设置logger的级别                |
| GET  |    /mappings    |    描述全部的URI路径,以及他们和映射器的映射关系     |
| GET  |    /metrics     |    获取应用程序度量信息, 内存用量和HTTP请求计数     |
| GET  | /scheduledtasks |              显示应用程序中的计划任务               |
| POST |    /shutdown    | 关闭应用程序,需要将endpoints.shutdown.enabled=true  |
| GET  |   /httptrace    |                 基本的http请求追踪                  |
| GET  |     /caches     |                    暴露可用缓存                     |

