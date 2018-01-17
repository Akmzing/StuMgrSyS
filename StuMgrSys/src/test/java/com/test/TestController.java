package com.test;
import java.util.List;


import com.sms.model.StudentEntity;
import com.sms.repository.StudentRepository;
import org.junit.*;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/springmvc-servlet.xml")
public class TestController {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSelectStudent(){
        List<StudentEntity> studentEntitys = studentRepository.findAll();
        System.out.println(studentEntitys.size());
    }
}
