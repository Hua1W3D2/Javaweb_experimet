package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Result;
import com.itheima.pojo.ReturnMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class AIController {
    @Autowired
    private ChatModel chatModel;
    /**
     * ChatModel 简单调用
     */
    @Log
    @GetMapping("/simple/chat")
    public Result simpleChat(@RequestParam(name = "message", defaultValue = "你好，很高兴认识你，请介绍一下自己。")String message) {
        log.info("传过来的字符串：{}",message);
        ReturnMessage returnMessage=new ReturnMessage(chatModel.call(message));
        return Result.success(returnMessage);
    }
}
