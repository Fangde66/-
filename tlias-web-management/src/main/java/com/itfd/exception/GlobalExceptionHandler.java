package com.itfd.exception;

import com.itfd.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice // 声明当前类是一个全局异常处理器
public class GlobalExceptionHandler {
    public static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler // 声明当前是异常处理的方法
    public Result handlerException(Exception e){
        log.error("程序出错",e); // 记录日志
        return Result.error("你已急哭"); // 给前端展示的信息
    }

    @ExceptionHandler
    public Result handlerDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出错",e);
        String message = e.getMessage(); // 获取完整错误信息
        int i = message.indexOf("Duplicate entry"); // 获取错误信息的精确位置
        String errMsg = message.substring(i); // 截取关键错误信息
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + "已存在");
    }
}
