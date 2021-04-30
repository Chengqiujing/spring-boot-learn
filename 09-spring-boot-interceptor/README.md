# spring-boot-interceptor

#### 1.自定义拦截器实现HandlerInterceptor接口
```java

public class LoginInterceptor implements HandlerInterceptor {
    //进入业务处理之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("已经进入了拦截器。。。");
        return true;
    }
    //进入逻辑处理之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器执行完毕。。。");
    }
    //完成整个请求
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("完成请求。。。");
    }
}
```

#### 自定义配置类

配置拦截器到url

可配置拦截路径  不拦截路径  

```java
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //拦截路径
        String[] addPathPatterns = {
                "/boot/**"
        };
        //不拦截路径
        String[] excludePathPatterns = {
                "/boot/index"
        };

        //注册登陆拦截器
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns(excludePathPatterns);

        //权限拦截器
        //registry.addInterceptor(new AuthorInterceptor()).addPathPatterns().excludePathPatterns();
    }
}
```


