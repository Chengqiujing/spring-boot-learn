package com.chengqj.study.characterencoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 设置字符集
     *
     * 等价于 web.xml里配置CharacterEncodingFilter
     * @return
     */
//    @Bean
//    public FilterRegistrationBean characterFilter(){
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setForceEncoding(true);
//        characterEncodingFilter.setEncoding("ISO-8859-1");
//
//        filterRegistrationBean.setFilter(characterEncodingFilter);
//        filterRegistrationBean.addUrlPatterns("/*");
//        return filterRegistrationBean;
//    }
}
