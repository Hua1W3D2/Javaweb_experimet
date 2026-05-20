package com.itheima.service;

import com.itheima.pojo.JobOption;
import com.itheima.pojo.StudentOption;

import java.util.List;
import java.util.Map;


public interface ReportService {
    /**
     * 统计各个职位的员工人数
     * @return
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别比例
     * @return
     */
    List<Map> getEmpGenderData();

    /**
     * 统计学生学历比例
     * @return
     */
    List<Map> getStudentDegreeData();

    /**
     * 班级人数统计
     * @return
     */
    StudentOption getStudentCountData();
}
