package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.StudentOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String ,Object>> list=empMapper.countEmpJobData();
        List Joblist=list.stream().map(dataMap->dataMap.get("pos")).toList();
        List dataList=list.stream().map(dataMap->dataMap.get("total")).toList();
        return new JobOption(Joblist,dataList);
    }

    @Override
    public List<Map> getEmpGenderData() {
        List<Map> list=empMapper.countEmpGenderData();
        return list;
    }

    @Override
    public List<Map> getStudentDegreeData() {
        List<Map> list= studentMapper.getStudentDegreeData();
        return list;
    }

    @Override
    public StudentOption getStudentCountData() {
        List<Map<String ,Object>> list=studentMapper.getStudentCountData();
        List clazzList=list.stream().map(dataMap->dataMap.get("cname")).toList();
        List dataList=list.stream().map(dataMap->dataMap.get("ccount")).toList();
        StudentOption studentOption = new StudentOption();
        studentOption.setClazzList(clazzList);
        studentOption.setDataList(dataList);

        return studentOption;
    }
}
