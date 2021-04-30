package com.chengqj.study.interceptor.config;

import com.chengqj.study.interceptor.impl.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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
