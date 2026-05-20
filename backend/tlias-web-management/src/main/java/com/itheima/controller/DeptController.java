package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

//    可以@RequestMapping(value = "/depts",method = RequestMethod.GET)//method指定请求方式

    /**
     * 查询所有员工
     * @return
     */
    @GetMapping//只能使用get请求查询
    public Result list(){
        //查询全部部门数据
        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        System.out.println("根据ID查询, id=" + id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 根据id删除员工
     * @param id
     * @return
     */
    @Log
    @DeleteMapping
    public Result delete(Integer id)  {
        System.out.println("根据ID删除部门:"+id);
        Result errResult=deptService.deleteById(id);
        if(errResult!=null){
            return errResult;
        }
        return Result.success();
    }

    /**
     * 添加部门
     * @param dept
     * @return
     */

    @Log
    @PostMapping
    public Result save(@RequestBody Dept dept){
        System.out.println("添加部门："+dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 修改部门
     * @param dept
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        System.out.println("修改部门, dept=" + dept);
        deptService.update(dept);
        return Result.success();
    }

}
