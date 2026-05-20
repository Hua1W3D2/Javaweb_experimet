package com.itheima;

import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@SpringBootTest
class TliasWebManagementApplicationTests {
//    @Autowired
//    ClazzMapper clazzMapper;
//    @Test
//    void contextLoads() {
//        Clazz clazz=new Clazz();
//        clazz.setName("java牢朱");
//        clazz.setBeginDate(LocalDate.now());
//        clazz.setEndDate(LocalDate.now());
//        clazz.setRoom("教四502");
//        clazz.setCreateTime(LocalDateTime.now());
//        clazz.setUpdateTime(LocalDateTime.now());
//        clazz.setMasterId(1);
//        clazz.setSubject(1);
//        clazz.setStatus("进行中");
//        clazzMapper.save(clazz);
//    }
//    @Test
//    void testFindAll(){
//        ClazzQueryParam clazzQueryParam = new ClazzQueryParam();
//        clazzQueryParam.setPage(1);
//        clazzQueryParam.setPageSize(4);
//        clazzQueryParam.setName("Java");
//        List<Clazz> all = clazzMapper.list(clazzQueryParam);
//        all.forEach(System.out::println);
//    }
//
//    @Test
//    void testclazzstu(){
//        List<Student> studentList=clazzMapper.studentsInClazz(1);
//        System.out.println(studentList);
//    }

}
