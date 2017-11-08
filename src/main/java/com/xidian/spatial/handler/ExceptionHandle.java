package com.xidian.spatial.handler;


import com.xidian.spatial.exception.BusinessException;
import com.xidian.spatial.models.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文件描述：异常捕获处理器
 * 创建作者：陈苗
 * 创建时间：2017/6/3 17:37
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult handle(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException be = (BusinessException) e;
            return ResponseResult.failure(be.getCode(), be.getMessage());
        } else {
            logger.error("[系统异常] : {}", e);
            return ResponseResult.failure(-1, "未知错误");
        }
    }
}
