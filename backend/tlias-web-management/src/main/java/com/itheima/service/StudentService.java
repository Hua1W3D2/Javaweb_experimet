package com.itheima.service;

import com.itheima.pojo.*;

import java.util.List;

public interface StudentService {
    /**
     * 分页查询学员
     * @param studentQueryParam
     * @return
     */
    public PageResult<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 根据id批量删除学生
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 保存学生数据
     * @param student
     */
    void save(Student student);

    /**
     * 更新学生数据
     * @param student
     */
    void update(Student student);

    /**
     * 根据id获取学生数据
     * @param id
     * @return
     */
    Student getById(Integer id);

    /**
     * 根据id进行违纪处理
     * @param id
     * @param score
     * @return
     */
    Result violationProcess(Integer id, Integer score);
}
