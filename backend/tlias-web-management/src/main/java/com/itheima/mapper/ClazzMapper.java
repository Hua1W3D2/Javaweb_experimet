package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 查询所有班级数据
     * @return
     */
    @Select("SELECT * from clazz")
    List<Clazz> findAll();

    /**
     * 分页查询班级数据
     * @param clazzQueryParam
     * @return
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 根据id删除班级
     * @param id
     */
    @Delete("delete from clazz where id=#{id}")
    void deleteById(Integer id);

    /**
     * 保存班级
     * @param clazz
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer save(Clazz clazz);

    /**
     * 根据id查询班级信息
     * @param id
     * @return
     */
    @Select("select * from clazz where id=#{id}")
    Clazz getById(Integer id);

    /**
     * 更新班级信息
     * @param clazz
     */
    void update(Clazz clazz);
    @Select("select * from student s where s.clazz_id=#{id}")
    public List<Student> studentsInClazz(Integer id);
}
