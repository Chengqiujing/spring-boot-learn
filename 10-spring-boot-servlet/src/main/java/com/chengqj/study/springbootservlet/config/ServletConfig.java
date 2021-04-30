package com.chengqj.study.springbootservlet.config;

import com.chengqj.study.springbootservlet.servlet.MyServletWithoutAnnotation;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean helloServletRegistrationBean(){

        String[] urlPartens = {
                "/myServletWithoutAnnotation"
        };

        ServletRegistrationBean registration = new ServletRegistrationBean( new MyServletWithoutAnnotation(),urlPartens);
        return registration;
    }

}
