package com.itheima.service;

import com.itheima.pojo.*;

import java.util.List;
import java.util.Map;

public interface EmpService {
    public List<Emp> findAll();
//    /**
//     * 分页查询
//     * @param page 页码
//     * @param pageSize 每页纪录数
//     * @return
//     */
//    public PageResult<Emp> page(Integer page, Integer pageSize) ;

    /**
     * 优化条件查询
     * @param empQueryParam
     * @return
     */
    public PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     * @param emp
     */

    public void save(Emp emp);

    /**
     * 根据id批量删除员工
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id获取员工信息
     * @param id
     * @return
     */
    Emp getInfo(Integer id);

    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 员工登录
     * @param emp
     * @return
     */
    LoginInfo login(Emp emp);

    /**
     * 根据Id获取员工密码
     * @param id
     * @return
     */
    Map<String,Object> getPasswordById(Integer id);

    /**
     * 保存员工密码数据
     * @param emp
     * @return
     */
    Result savePassword(Emp emp);
}
