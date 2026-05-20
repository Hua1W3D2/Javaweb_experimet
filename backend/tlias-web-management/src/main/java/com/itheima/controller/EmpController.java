package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping
public class EmpController {
    /**
     * 分页查询
     * @return
     */
    @Autowired
    private EmpService empService;

//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
//        log.info("分页查询：{}，{}",page,pageSize);
//        PageResult<Emp> pageResult= empService.page(page,pageSize);
//        return Result.success(pageResult);
//    }

    /**
     * 查询所有员工
     * @return
     */
    @GetMapping("/emps/list")
    public Result list(){
        List<Emp> empList=empService.findAll();
        return Result.success(empList);
    }

    /**
     * 分页查询
     * @param empQueryParam
     * @return
     */
    @GetMapping("/emps")
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询：{}",empQueryParam);
        PageResult<Emp> pageResult= empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加员工
     * @param emp
     * @return
     */
    @Log
    @PostMapping("/emps")
    public Result save(@RequestBody Emp emp){
        log.info("请求参数emp: {}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 根据id列表删除员工
     * @param ids
     * @return
     */
    @Log
    @DeleteMapping("/emps")
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除部门: ids={} ", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }
    /**
     * 查询回显
     */
    @GetMapping("/emps/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工的详细信息");
        Emp emp  = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     */
    @Log
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息, {}", emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 根据id查询用户密码回显
     * @param id
     * @return
     */
    @GetMapping("/emps/pwd/{id}")
    public Result getPassword(@PathVariable  Integer id){
        Map<String,Object> passwordMap=empService.getPasswordById(id);
        return Result.success(passwordMap);
    }

    /**
     * 修改密码
     * @param emp
     * @return
     */
    @PutMapping("/emps/pwd")
    public Result savePassword(@RequestBody Emp emp){
        Result errResult=empService.savePassword(emp);
        if(errResult!=null){
            return errResult;
        }
        return Result.success();
    }

}
