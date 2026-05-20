package com.itheima.controller;

import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.pojo.StudentOption;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportService reportService;
    /**
     * 统计员工个数
     * @return
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计各个职位的员工人数");
        JobOption empJobData = reportService.getEmpJobData();
        return Result.success(empJobData);
    }

    /**
     * 统计员工性别比例
     * @return
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计各个职位员工性别比例");
        List<Map> genderList=reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 统计学员学历比例
     * @return
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计各个学员学历比例");
        List<Map> studentList=reportService.getStudentDegreeData();
        return Result.success(studentList);
    }

    /**
     * 统计每一个班级的人数
     * @return
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计每一个班级的人数");
        StudentOption studentOption=reportService.getStudentCountData();
        return Result.success(studentOption);
    }
}
