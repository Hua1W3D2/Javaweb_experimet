package com.itheima.utils;

import com.itheima.pojo.Result;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {

        String message = e.getMessage();
        if(message.contains("emp.username")){
            return Result.error("用户名已存在");
        } else if (message.contains("emp.phone")) {
            return Result.error("用户手机号已存在");
        }
        if(message.contains("clazz.name")){
            return Result.error("已存在该班级");
        }
        if (message.contains("student.no")) {
            return Result.error("学号已存在");
        }else if (message.contains("student.phone")) {
            System.out.println(message);
            return Result.error("手机号已存在");
        }  else if (message.contains("student.id_card")) {
            return Result.error("身份证号已存在");
        }
        return Result.error("数据重复，请检查输入");
    }

    //处理异常
    @ExceptionHandler
    public Result ex(Exception e){//方法形参中指定能够处理的异常类型
        e.printStackTrace();//打印堆栈中的异常信息
        //捕获到异常之后，响应一个标准的Result
        if(e.getMessage().contains("部门人数不为0")){
            return Result.error("部门人数不为0，不可删除部门");
        }
        if(e.getMessage().contains("日期填写错误")){
            return Result.error(e.getMessage());
        }
        return Result.error("对不起,操作失败,请联系管理员");
    }

}