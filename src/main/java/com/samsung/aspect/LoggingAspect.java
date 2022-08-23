package com.samsung.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    @Around("execution(* com.samsung.dao.QuestionDao.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = LogManager.getLogger(LoggingAspect.class);
        log.debug("Class name: {}", joinPoint.getTarget().getClass().getName());
        log.debug("Start of Method: {}", joinPoint.getSignature().getName());
        Object object = joinPoint.proceed();
        log.debug("End of Method: {}", joinPoint.getSignature().getName());
        return object;
    }
}
