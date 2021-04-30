package com.chengqj.study.springbootjackson;

import com.chengqj.study.springbootjackson.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void testJackson() throws JsonProcessingException {
        // jacksond的ObjectMapper 转换对象
        ObjectMapper mapper = new ObjectMapper();

        List<User> list = new ArrayList<User>() {{
            add(User.builder().name("lisi").age(15).build());
            add(User.builder().name("zhangsan").age(25).build());
        }};

        // 序列化
        String s = mapper.writeValueAsString(list);
        System.out.println(s);
        // 反序列化对象
        // 注意:对象一定要有无参构造和全参构造
        User user = mapper.readValue("{\"name\":\"lisi\",\"age\":15}", User.class);
        System.out.println(user);
        // 反序列化数组
        List<User> list1 = mapper.readerForListOf(User.class).readValue("[{\"name\":\"lisi\",\"age\":15},{\"name\":\"zhangsan\",\"age\":25}]");
        System.out.println(list1);
    }

}
