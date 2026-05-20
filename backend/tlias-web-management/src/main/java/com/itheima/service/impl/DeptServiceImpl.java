package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result deleteById(Integer id) {
        Integer count=empMapper.getCountByDeptId(id);
        if(count!=0){
            return Result.error("部门人数不为0，不可删除部门");
//            throw new Exception("部门人数不为0，不可删除部门");
        }
        deptMapper.deleteById(id);
        return null;
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
}
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }
}
