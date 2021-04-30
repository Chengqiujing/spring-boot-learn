


### 方案一：注解式配置

1. 启动类指定 功能启动注解 `@ServletComponentScan(basePackages = "com.chengqj.study.springbootservlet.servlet")`

2. 编写一个继承指定规则接口（HttpServlet）的类，在类上标记一个注解@WebServlet(urlPatterns = "/myServlet")指定要扫描的包

### 方案二：自定义类配置

1. 自定义类上标记@Configuration

2. 类中指定要声明的bean为ServletRegistrationBean

```java
    @Bean
    public ServletRegistrationBean helloServletRegistrationBean(){
        
        String[] urlPartens = {
                "/myServletWithoutAnnotation"
        };
        
        ServletRegistrationBean registration = new ServletRegistrationBean( new MyServletWithoutAnnotation(),urlPartens);
        return registration;
    }
```