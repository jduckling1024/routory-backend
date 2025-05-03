package com.project.routorybackend.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@Slf4j
public class TraceIdLogAspect {
    @Around(value = "@within(com.project.routorybackend.config.TraceIdLog)")
    public Object methodLog(ProceedingJoinPoint joinPoint) throws Throwable {
        ThreadContext.put("traceId", UUID.randomUUID().toString().substring(0, 8));

        try {
            return joinPoint.proceed();
        } finally {
            ThreadContext.remove("traceId");
        }
    }
}
