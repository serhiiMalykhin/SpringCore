package com.spring.core.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StatisticAspect {
    private Map<Class<?>, Integer> counter;

    public StatisticAspect() {
        counter = new HashMap<>();
    }

    @AfterReturning(pointcut = "target(com.spring.core.logger.EventLogger) && execution(* logEvent (..))")
    public void count(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        if(!counter.containsKey(clazz)){
            counter.put(clazz, 0);
        }
        counter.put(clazz, counter.get(clazz) + 1);
    }

    @Override
    public String toString() {
        return "StatisticAspect{" +
                "counter=" + counter +
                '}';
    }
}
