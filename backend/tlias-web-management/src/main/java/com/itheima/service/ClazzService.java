package com.itheima.service;

import com.itheima.pojo.*;

import java.util.List;

public interface ClazzService {
    /**
     * 查询所有班级
     * @return
     */
    public List<Clazz> findAll();

    /**
     * 分页查询班级数据
     * @param clazzQueryParam
     * @return
     */
    public List<Clazz> list(ClazzQueryParam clazzQueryParam);
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 根据id删除班级
     * @param id
     * @return
     */
    Result deleteById(Integer id);

    /**
     * 保存班级数据
     * @param clazz
     * @return
     */
    Result save(Clazz clazz);

    /**
     * 根据id删除班级
     * @param id
     * @return
     */
    Clazz getById(Integer id);

    /**
     * 更新班级数据
     * @param clazz
     * @return
     */
    Result update(Clazz clazz);
}
