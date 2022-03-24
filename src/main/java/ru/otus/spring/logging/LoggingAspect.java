package ru.otus.spring.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Before("within(ru.otus.spring.service..*)")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Метод: " + joinPoint.getSignature().getName());
        System.out.println("Аргументы: " + Arrays.toString(joinPoint.getArgs()));
    }
}
