package com.chengqj.study.springbootweb.configproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author chengqj
 * @Date 2020/12/1 14:02
 * @Desc
 */
@Component // 必须有
@ConfigurationProperties(prefix = "test")
@PropertySource("classpath:application-test.properties") // 指定配置文件
public class ConfigByTestProperties {
    private String name;
    private Integer age;
    private String address;

    public ConfigByTestProperties() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ConfigByTestProperties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
