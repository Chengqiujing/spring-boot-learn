# spring-boot-javaobject

不启动tomcat 类似纯spring应用

### 方式一：直接在启动类中main函数执行

启动类中，利用springboot 容器对象 ConfigurableApplicationContext来获取bean

```java
//利用容器对现象获取 bean
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        HelloService helloServiceImpl = (HelloService) run.getBean("helloServiceImpl");
        helloServiceImpl.sayHello();

```

### 方式二：实现接口 CommandLineRunner 中的run

springboot启动后会调用的一个钩子函数，类似java项目中的main入口

``` java 
    @Autowired
    HelloService helloService;
    // 类似纯java 入口main函数
    @Override
    public void run(String... args) throws Exception {
        helloService.sayHello();
    }
```