package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzQueryParam {
    private String name;//班级名称
    private LocalDate begin;//开始时间
    private LocalDate end;//结束时间
    private Integer page=1;//页码
    private Integer pageSize=5;//每页展示纪录数
}
