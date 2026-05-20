package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;


import java.util.Arrays;
import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门
     * @return
     */
    @Select("select * from dept order by update_time desc")
    List<Dept> findAll();

    /**
     * 根据id删除部门
     * @param id
     */
    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    /**
     * 插入部门信息
     * @param dept
     */
    @Insert("insert into dept (name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    /**
     * 更新部门信息
     * @param dept
     */
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);

    /**
     * 根据ID查询部门
     * @param id
     * @return
     */
    @Select("SELECT * from dept where id=#{id}")
    Dept getById(Integer id);
}
