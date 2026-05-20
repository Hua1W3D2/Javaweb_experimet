package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * 学生分页查询
     * @param studentQueryParam
     * @return
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("学生分页查询：{}",studentQueryParam);
        PageResult<Student> pageResult= studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加学生
     * @param student
     * @return
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Student student){
        System.out.println("添加学生:" + student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 根据id查询学生
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询学生"+id);
        Student student=studentService.getById(id);
        return Result.success(student);
    }

    /**
     * 根据id删除学员
     * @param ids
     * @return
     */
    @Log
    @Transactional(rollbackFor = {Exception.class})
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids) {
        System.out.println("根据ID删除学生:" + ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 修改学员
     * @param student
     * @return
     */
    @Log
    @Transactional(rollbackFor = {Exception.class})
    @PutMapping
    public Result update(@RequestBody Student student){
        studentService.update(student);
        return Result.success();
    }

    /**
     * 学生违纪扣分
     * @param id
     * @param score
     * @return
     */
    @Log
    @PutMapping("/violation/{id}/{score}")
    public Result violationProcess(@PathVariable Integer id,@PathVariable Integer score){
        Result errResult=studentService.violationProcess(id,score);
        if(errResult!=null){
            return errResult;
        }
        return Result.success();
    }
}
