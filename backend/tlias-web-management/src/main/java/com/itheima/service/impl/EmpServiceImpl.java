package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.调用mapper接口，查询结果列表
        List<Emp> empList=empMapper.list(empQueryParam);
        Page<Emp> p=(Page<Emp>) empList;
        //3.封装结果，pageResult
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        try {
            //1.补全基础属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());

            //2.保存员工基本信息
            empMapper.insert(emp);

            //3.批量保存员工工作经历信息
            Integer empId = emp.getId();
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr -> empExpr.setEmpId(empId));
                empExprMapper.insertBatch(exprList);
            }
        } finally {

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(List<Integer> ids) {
        //1. 根据ID批量删除员工基本信息
        empMapper.deleteByIds(ids);
        //2. 根据员工的ID批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Emp emp) {
        //1. 根据ID更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2. 根据员工ID删除员工的工作经历信息 【删除老的】
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //3. 新增员工的工作经历数据 【新增新的】
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        //1.调用mapper接口，根据用户名和密码查询员工信息
        Emp empLogin = empMapper.getUsernameAndPassword(emp);
        //2.判断是否存在员工，如果存在则组装成功登录信息
        if(empLogin != null){
            log.info("登录成功，员工信息为，{}",emp);
            //生成jwt令牌
            Map<String, Object> claim=new HashMap<>();
            claim.put("id",empLogin.getId());
            claim.put("username",empLogin.getUsername());
            String jwtToken=JwtUtils.generateJwt(claim);
            LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwtToken,empLogin.getImage());

            return loginInfo;
        }
        //3.不存在则返回null
        return null;

    }

    /**
     * 根据id返回密码
     * @param id
     * @return
     */
    @Override
    public Map<String,Object> getPasswordById(Integer id) {
        String password= empMapper.getPasswordById(id);
        Map<String,Object> map=new HashMap<>();
        map.put("password",password);
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result savePassword(Emp emp) {
        if(emp.getPassword()==null||emp.getPassword().isEmpty()){
            return Result.error("修改后的密码不许为空！！！");
        }
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.savePasswordById(emp);
        return null;
    }
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
////        //1.调用mapper接口查询总记录数
////        Long total=empMapper.count();
////        //2.调用mapper接口，查询结果列表
////        Integer start=(page-1)*pageSize;
////        List<Emp> rows=empMapper.list(start,pageSize);
////        //3.封装结果，pageResult
////        return new PageResult<Emp>(total,rows);
//        //1.设置分页参数
//        Long total=empMapper.count();
//        PageHelper.startPage(page,pageSize);
//        //2.调用mapper接口，查询结果列表
//        List<Emp> empList=empMapper.list();
//        Page<Emp> p=(Page<Emp>) empList;
//        //3.封装结果，pageResult
//        return new PageResult<Emp>(p.getTotal(),p.getResult());
//
//    }
}
