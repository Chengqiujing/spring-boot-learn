package com.chengqj.study.springbootfilter.config;

import com.chengqj.study.springbootfilter.filter.MyFilterWithoutAnnotation;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean getFilterRegistration(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new MyFilterWithoutAnnotation());
        String[] urlPatterns = {
                "/filter"
        };
        registrationBean.addUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
