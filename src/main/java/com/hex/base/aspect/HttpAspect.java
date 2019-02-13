package com.hex.base.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * User: hexuan
 * Date: 2017/8/14
 * Time: 下午1:54
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {
    // private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.hex.base.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBegin(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("");
        // url
        log.info("* url={}", request.getRequestURI());
        // method
        log.info("* method={}", request.getMethod());
        // ip
        log.info("* ip={}", request.getRemoteAddr());
        // 类方法
        log.info("* class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // 参数
//        log.info("* args={}", joinPoint.getArgs());
        log.info("");
    }

//    @After("log()")
//    public void doAfter() {
//        logger.info("2222222222");
//    }

//    @AfterReturning(returning = "object", pointcut = "log()")
//    public void doAfterReturning(Object object) {
//        log.info("");
//        log.info("* response={}", object.toString());
//        log.info("");
//    }
}
