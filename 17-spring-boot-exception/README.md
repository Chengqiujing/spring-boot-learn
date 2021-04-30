spring boot 处理异常有三种方式

#### 1.自定义异常（推荐使用） 

1) 实现HandlerExceptionResolver接口中resolveException

并在其中判断异常类型，然后利用ModelAndView跳转错误页面，或者记录日志

```java
@Configuration
public class SelfExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        if(e instanceof NullPointerException){
            modelAndView.setViewName("error");
        }else if(e instanceof ArithmeticException){
            modelAndView.setViewName("error2");
        }
        modelAndView.addObject("error",e);
        return modelAndView;
    }
}
```

#### 2. 全局异常处理

1) 自定义类并标记注解@ControllerAdvice

2) 在自定义类中声明返回值类型为 ModelAndView 的方法,并在方法上标注@ExceptionHandler(NullPointerException.class),括号内为异常类型

3) ModelAndView 对象中可以指定跳转页面 和 异常对象
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ModelAndView nullPointerExeption(Exception e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("error",e);
        return modelAndView;
    }
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView arithmeticExeption(Exception e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error2");
        modelAndView.addObject("error",e);
        return modelAndView;
    }
}
```

#### 3. 全局异常简单方式处理

1) 配置文件中返回一个 SimpleMappingExceptionResolver 类型的Bean

声明SimpleMappingExceptionResolver实例,指定异常类型和错误页面
```java
@Configuration
public class SimpleGlobalException {

    /**
     * 这种方式不能返回异常信息，不好
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        /**
         * 参数一：异常类型，必须是异常类型的全名
         * 参数二：视图名称
         */
        mappings.put("java.lang.NullPointerException","error");
        mappings.put("java.lang.ArithmeticException","error2");
        resolver.setExceptionMappings(mappings);
        return resolver;
    }
}
```

#### 4.感觉最好的还是AOP 自己处理异常