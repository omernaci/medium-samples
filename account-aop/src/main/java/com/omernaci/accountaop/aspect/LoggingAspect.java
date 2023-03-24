package com.omernaci.accountaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.omernaci.accountaop.service.AccountService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Before " + joinPoint.getSignature().getName() + "()");
    }

    @AfterReturning(pointcut = "execution(* com.omernaci.accountaop.service.AccountService.*(..))",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("After " + joinPoint.getSignature().getName() + "(): " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.omernaci.accountaop.service.AccountService.*(..))",
            throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        logger.error("Exception in " + joinPoint.getSignature().getName() + "(): " + exception.getMessage());
    }

}
