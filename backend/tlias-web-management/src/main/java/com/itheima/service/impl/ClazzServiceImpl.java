package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    EmpMapper empMapper;
    @Autowired
    ClazzMapper clazzMapper;

    /**
     * 查询所有班级
     *
     * @return
     */
    @Override
    public List<Clazz> findAll() {
        List<Clazz> clazzList=clazzMapper.findAll();
        for (Clazz clazz : clazzList) {
            Emp emp=empMapper.getById(clazz.getMasterId());
            if(emp!=null) {
                clazz.setMasterName(emp.getName());
            }
            if(clazz.getBeginDate().isAfter(LocalDate.now())){
                clazz.setStatus("未开课");
            }else if(clazz.getEndDate().isAfter(LocalDate.now())){
                clazz.setStatus("进行中");
            }else {
                clazz.setStatus("已结课");
            }

        }
        return clazzList;
    }

    @Override
    public List<Clazz> list(ClazzQueryParam clazzQueryParam){
        List<Clazz> clazzList=clazzMapper.list(clazzQueryParam);
        for (Clazz clazz : clazzList) {
            Emp emp=empMapper.getById(clazz.getMasterId());
            if(emp!=null) {
                clazz.setMasterName(emp.getName());
            }
            if(clazz.getBeginDate().isAfter(LocalDate.now())){
                clazz.setStatus("未开课");
            }else if(clazz.getEndDate().isAfter(LocalDate.now())){
                clazz.setStatus("进行中");
            }else {
                clazz.setStatus("已结课");
            }

        }
        return clazzList;
    }

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        //2.调用mapper接口，查询结果列表
        List<Clazz> clazzList=clazzMapper.list(clazzQueryParam);
        for (Clazz clazz : clazzList) {
            Emp emp=empMapper.getById(clazz.getMasterId());
            if(emp!=null) {
                clazz.setMasterName(emp.getName());
            }
            if(clazz.getBeginDate().isAfter(LocalDate.now())){
                clazz.setStatus("未开课");
            }else if(clazz.getEndDate().isAfter(LocalDate.now())){
                clazz.setStatus("进行中");
            }else {
                clazz.setStatus("已结课");
            }

        }
        Page<Clazz> p=(Page<Clazz>) clazzList;
        //3.封装结果，pageResult
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteById(Integer id) {
        List<Student> students=clazzMapper.studentsInClazz(id);
        if(!students.isEmpty()){
            return Result.error("班级里面存在学生，不可删除班级！！！");
        }
        clazzMapper.deleteById(id);
        return null;
    }

    /**
     * 添加班级
     * @param clazz
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result save(Clazz clazz)  {
        LocalDate beginDate=clazz.getBeginDate();
        if(beginDate.isAfter(clazz.getEndDate())){
//            throw new Exception("日期填写错误！！！ 开始日期不能晚于结束日期");
            return Result.error("日期填写错误！！！ 开始日期不能晚于结束日期");
        }
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.save(clazz);
        return null;
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result update(Clazz clazz) {
        if(clazz.getBeginDate().isAfter(clazz.getEndDate())){
            return Result.error("日期填写错误！！！ 开始日期不能晚于结束日期");
        }
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
        return null;
    }

}
