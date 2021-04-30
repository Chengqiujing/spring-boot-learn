package com.chengqj.study.springbootcache.service;

import com.chengqj.study.springbootcache.entity.Student;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @package: com.chengqj.study.springbootcache.service
 * @description:
 * @author: chengqj
 * @date: Created in 2021/2/21 23:04
 * @copyright: Copyright (c) 2021
 * @version: V1.0
 * @modified:
 */
@Service
public class StudentService {

    private final static String NAME = "test_cache";

    @Cacheable(value = NAME, key = "#id")
    public Student queryStudent(String id) {
        Student student = new Student();
        student.setId(id);
        student.setName("lisi");
        student.setAge(20);
        student.setAddress("abcd");
        return student;
    }

    @Cacheable(value = NAME, key = "#id")
    public Student queryStudentById(String id) {
        return new Student();
    }

    @Cacheable(value = NAME, key = "#id")
    public Student addStudent(Student student) {
        return student;
    }


    @CacheEvict(value = NAME, key = "#id")
    public void deleteById(String id) {

    }

    @CachePut(value = NAME, key = "#student.id")
    public Student updateStudent(Student student) {
        return student;
    }
}
