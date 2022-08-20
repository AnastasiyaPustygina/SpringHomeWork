package com.samsung.aspect;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    @Before("execution(* com.samsung.dao.QuestionDaoSimple.*(..))")
    public void logBefore(JoinPoint joinPoint){
        Logger log = LoggerFactory.getLogger(LoggingAspect.class);
        log.debug("Class name: {}", joinPoint.getTarget().getClass().getName());
        log.debug("Method: {}", joinPoint.getSignature().getName());
    }
}
