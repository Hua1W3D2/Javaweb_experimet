package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    /**
     * 根据ID查询员工详细信息
     */
    public Emp getById(Integer id) ;

    /**
     * 查询总记录数
     * @return
     */
    @Select("select  count(*) from  emp e left join dept d on e.dept_id=d.id")
    public Long count();

    /**
     * 分页查询
     * @return
     */
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id order by update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start,Integer pageSize);

    /**
     * 使用pageHelper
     * @return
     */
    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id order by update_time desc")
    public List<Emp> findAll();

    /**
     * 优化参数
     * @return
     */
//   @Select("select e.*, d.name deptName from emp as e left join dept as d on e.dept_id = d.id where e.name like concat('%',#{name},'%') and e.gender = #{gender} and e.entry_date between #{begin} and #{end}")
      public  List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工数据
     * @param emp
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp (username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{password},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    public void insert(Emp emp);

    /**
     * 根据id列表删除员工
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id更新员工
     * @param emp
     */
    void updateById(Emp emp);

    /**
     * 统计各个职位的员工人数
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工男女性别各个人数
     * @return
     */
    @MapKey("pos")
    List<Map> countEmpGenderData();

    /**
     * 根据部门id查询该部门下有多少员工
     * @param id
     * @return
     */
    @Select("select count(*) from emp e where e.dept_id=#{id}")
    Integer getCountByDeptId(Integer id);

    /**
     * 根据用户名和密码查询员工信息
     * @param emp
     * @return
     */
    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getUsernameAndPassword(Emp emp);

    /**
     * 根据用户id获得密码
     */
    @Select("select e.password from emp  e where id=#{id}")
    String getPasswordById(Integer id);

    /**
     * 根据用户id更新密码
     * @param emp
     */
    @Update("update emp set password=#{password} where id=#{id}")
    void savePasswordById(Emp emp);
}
