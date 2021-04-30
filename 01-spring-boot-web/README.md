# spring-boot-web

### 配置文件

`server.port=8080`

` server.servlet.context-path=/spring-boot-web`      
配置上下文路径，其中一个具体表现为:在访问url上必须在根路径加配置名访问       

### 多环境

可以在 默认配置文件 ： application.properties 中指定激活哪一个        

`spring.profiles.active=xx`

多个配置文件名 格式如：      
**application-xx.properties**

### 自定义配置

properties

```properties
selfdefination.name=zhangsan
```

两种读取方式：

1. 属性上加：`@value('${selfdefination.name}')`

2. 或者要赋值类上加注解： `@Component @ConfigurationProperties(prefix = "selfdefination")`

```java
// 第一种 利用前缀,这种前缀后必须和成员变量匹配
@Component // 必须有
@ConfigurationProperties(prefix = "selfdefination") // 前缀
public class ConfigInfo {
    ...
}

// 第二种使用@Value
@Component
public class SelfConfigurationContoller {
    @Value("${selfdefination.name}")
    private String name;
}
```

还可以用`@PropertySource("classpath:application-test.properties")`指定配置文件

```java
@Component // 必须有
@ConfigurationProperties(prefix = "test") // 指定文件
@PropertySource("classpath:application-test.properties")
public class ConfigByTestProperties {
    private String name;
    private Integer age;
    private String address;
}
```

> 如果配置类上没有@Component注解, 则使用类还需要配置注解@EnableConfigurationProperties({ConfigByTestProperties.class}), 这两个注解不可同时使用

### SpringMVC 几个注解

@RestController

@PostMapping

@GetMapping

@PutMapping

@DeleteMapping

### 关闭控制台logo

启动类修改

```java
public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        //关闭启动Spring logo
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
```
