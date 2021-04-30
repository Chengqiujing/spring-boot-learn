
application.properties

```properties
# actuator监控端口(端口不配置 默认和server一样)
management.server.port=8100
management.server.servlet.context-path=/chengqj
#默认之开启了health和info，设置为*，则包含所有的web入口端点
management.endpoints.web.exposure.include=*
```

访问举例

`http://localhost:8080/chengqj/actuator/health`

#### actuator提供的主要功能

