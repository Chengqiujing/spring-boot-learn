package com.chengqj.study.springbootcache;

import com.chengqj.study.springbootcache.entity.Student;
import com.chengqj.study.springbootcache.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    StudentService service;

    @Test
    void testAddCache() {

        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        student.setName("zhangsan");
        student.setAge(20);
        student.setAddress("abcd");
        service.addStudent(student);

    }

}
