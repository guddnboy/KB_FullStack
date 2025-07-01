package org.scoula.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration          // Spring 설정 클래스임을 선언
@ComponentScan(basePackages = {
        "org.scoula.advice",           // AOP Advice 패키지 스캔
        "org.scoula.sample.service"    // 비즈니스 서비스 패키지 스캔
})
@EnableAspectJAutoProxy    // AspectJ Auto Proxy 활성화 (핵심!)
public class RootConfig {
    // 추가 Bean 설정이 필요한 경우 여기에 작성
}
