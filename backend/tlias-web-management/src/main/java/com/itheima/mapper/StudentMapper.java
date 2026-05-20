package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /**
     * 查询所有学员
     * @param studentQueryParam
     * @return
     */
    List<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 根据id批量删除
     * @param ids
     */
    void deleteByID(List<Integer> ids);

    /**
     * 添加学员
     * @param student
     */
    void save(Student student);

    /**
     * 更新信息
     * @param student
     */
    void update(Student student);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select s.*,c.name clazz_name from student s,clazz c where s.id=#{id} and s.clazz_id=c.id")
    Student getById(Integer id);

    /**
     * 违纪处理
     * @param student
     */
    @Update("update student set violation_count=#{violationCount},violation_score=#{violationScore} where id=#{id}")
    void violationProcess(Student student);

    /**
     * 获取学历占比数据
     * @return
     */
    @MapKey("pos")
    List<Map> getStudentDegreeData();

    /**
     * 获取学生各个班级人数数据
     * @return
     */
    @MapKey("cname")
    List<Map<String, Object>> getStudentCountData();

}
