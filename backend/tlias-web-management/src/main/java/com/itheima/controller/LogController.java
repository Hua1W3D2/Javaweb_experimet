package com.itheima.controller;

import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    OperateLogService operateLogService;

    /**
     * 日志分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询{},{}",page,pageSize);
        PageResult<OperateLog> pageResult=operateLogService.page(page,pageSize);
        return Result.success(pageResult);
    }
}
