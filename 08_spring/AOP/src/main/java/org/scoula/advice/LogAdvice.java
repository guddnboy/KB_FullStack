package org.scoula.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect          // AOP Aspect임을 선언
@Slf4j          // Lombok 로깅 어노테이션
@Component      // Spring Bean으로 등록
public class LogAdvice {

    // SampleService로 시작하는 모든 클래스의 모든 메서드 실행 전에 동작
    @Before("execution(* org.scoula.sample.service.SampleService*.*(..))")
    public void logBefore() {
        log.info("==================================");
    }

    @Before("execution(* org.scoula.sample.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
    public void logBeforeWithParam(String str1, String str2) {
        log.info("str1: " + str1);  // 첫 번째 파라미터 로깅
        log.info("str2: " + str2);  // 두 번째 파라미터 로깅
    }

    @AfterThrowing(
            pointcut = "execution(* org.scoula.sample.service.SampleService*.*(..))",
            throwing = "exception"  // 예외 객체를 매개변수로 전달
    )
    public void logException(Exception exception) {
        log.info("Exception...!!!!");
        log.info("exception: " + exception);  // 예외 정보 로깅
    }

    /* @Around 실행 흐름
1. @Around Advice 시작
2. proceed() 호출 전 작업 (Before와 같은 역할)
3. proceed() → Target 메서드 실행
4. proceed() 호출 후 작업 (After와 같은 역할)
5. 최종 결과 반환
*/
    @Around("execution(* org.scoula.sample.service.SampleService*.*(..))")
    public Object logTime(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();  // 시작 시간 기록

        // Target 객체 정보 로깅
        log.info("Target: " + pjp.getTarget());
        // 전달된 파라미터 로깅
        log.info("Param: " + Arrays.toString(pjp.getArgs()));

        Object result = null;
        try {
            result = pjp.proceed();  // 실제 메서드 실행
        } catch(Throwable e) {
            e.printStackTrace();     // 예외 처리
        }

        long end = System.currentTimeMillis();    // 종료 시간 기록
        log.info("TIME: " + (end - start) + "ms");       // 실행 시간 로깅

        return result;  // 결과 반환
    }
}