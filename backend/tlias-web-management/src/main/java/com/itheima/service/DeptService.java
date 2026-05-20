package com.itheima.service;


import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
     * @return
     */
    List<Dept> findAll();

    /**
     * 根据id删除部门
     * @param id
     * @return
     */
    Result deleteById(Integer id);

    /**
     * 添加部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 更新部门数据
     * @param dept
     */
    void update(Dept dept);

    /**
     * 根据id获取部门数据
     * @param id
     * @return
     */
    Dept getById(Integer id);
}
