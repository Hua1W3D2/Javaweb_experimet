package com.itheima.service;

import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;

public interface OperateLogService {
    /**
     * 分页查询日志记录
     * @param page
     * @param pageSize
     * @return
     */
    PageResult<OperateLog> page(Integer page, Integer pageSize);
}
