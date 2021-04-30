# spring-boot-characterencoding

针对请求输入输出流设置编码格式


### 方案一：spring自带filter

这里filter的配置方式使用 **自定义配置类** 来配置（这里在启动类中，本身具有@Configuration）,
是因为这个类Spring已经提供这个类-CharacterEncodingFilter，我们只需要实例化
后，注册到spring中就好。


ps:要在核心配置文件里指定`spring.http.encoding.enabled=false` 为false
这对Spring boot好像不是很好使，对servlet好像可以，应该式spring中有配置我没有发现


### 方案二：

spring boot下在核心配置文件中配置

```
spring.http.encoding.enabled=true
spring.http.encoding.charset=utf-8
spring.http.encoding.force=true
```
