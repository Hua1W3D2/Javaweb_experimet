package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.*;

import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        //2.调用mapper接口，查询结果列表
        List<Student> studentList= studentMapper.list(studentQueryParam);
        Page<Student> p=(Page<Student>) studentList;
        //3.封装结果，pageResult
        return new PageResult<Student>(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByID(ids);
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        student.setViolationCount(0);
        student.setViolationScore(0);
        studentMapper.save(student);
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Result violationProcess(Integer id, Integer score) {
        if(score<=0){
            return Result.error("违纪分数扣分必须大于0！！！");
        }
        Student student= studentMapper.getById(id);
        Integer oldcore=student.getViolationScore();
        Integer count=student.getViolationCount();
        student.setViolationCount(count+1);
        student.setViolationScore(oldcore+score);
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.violationProcess(student);
        return null;
    }
}
