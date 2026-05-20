package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;
//
//    //    @RequestMapping(value = "/depts",method = RequestMethod.GET)//method指定请求方式

    /**
     * 查询所有班级
     * @param clazzQueryParam
     * @return
     */
    @GetMapping("/list")//只能使用get请求查询
    public Result list(ClazzQueryParam clazzQueryParam){
        //查询全部班级数据
        List<Clazz> clazzList=clazzService.list(clazzQueryParam);
        return Result.success(clazzList);
    }

    /**
     * 分页查询班级
     * @param clazzQueryParam
     * @return
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("分页查询：{}",clazzQueryParam);
        PageResult<Clazz> pageResult= clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }
//

    /**
     * 根据id删除班级
     * @param id
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable  Integer id) {
        System.out.println("根据ID删除班级:" + id);
        Result errResult=clazzService.deleteById(id);
        if(errResult!=null){
            return errResult;
        }
        return Result.success();
    }

    /**
     * 根据ID查询班级
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable  Integer id) {
        System.out.println("根据Id查询班级:" + id);
        Clazz clazz=clazzService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 添加班级数据
     * @param clazz
     * @return
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        System.out.println("添加班级数据:"+clazz);
        Result errResult=clazzService.save(clazz);
        if(errResult!=null){
            return errResult;
        }
        return Result.success();
    }

    /**
     * 修改班级信息
     * @param clazz
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级信息, {}", clazz);
        Result errResult=clazzService.update(clazz);
        if(errResult!=null){
            return errResult;
        }
        return Result.success();
    }
}
