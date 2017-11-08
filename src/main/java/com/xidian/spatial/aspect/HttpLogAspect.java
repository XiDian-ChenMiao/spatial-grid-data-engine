package com.xidian.spatial.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件描述：Http请求日志切面
 * 创建作者：陈苗
 * 创建时间：2017/6/3 14:50
 */
@Aspect
@Component
public class HttpLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpLogAspect.class);

    @Pointcut("execution(public * com.xidian.spatial.controller.*.*(..))")
    public void log() {}

    @Before("log()")
    public void logBefore(JoinPoint point) {
        ServletRequestAttributes holder = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = holder.getRequest();
        logger.info("url = {}", request.getRequestURL());
        logger.info("method = {}", request.getMethod());
        logger.info("ip = {}", request.getRemoteAddr());
        logger.info("class method = {}", point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        logger.info("args = {}", point.getArgs());
    }

    @After("log()")
    public void logAfter() {

    }

    @AfterReturning(value = "log()", returning = "resp")
    public void doReturning(Object resp) {
        logger.info("response = {}", resp);
    }
}
