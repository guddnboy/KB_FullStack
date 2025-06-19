package org.scoula.sample.service;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)      // JUnit 5에서 Spring 테스트 지원
@ContextConfiguration(classes = { RootConfig.class })  // Spring 설정 클래스 지정
@Slf4j
class SampleServiceTest {

    @Autowired
    private SampleService service;  // 실제로는 Proxy 객체가 주입됨

    @Test
    public void doAdd() throws Exception {
        // 정상적인 덧셈 테스트
        log.info("덧셈 결과 : " + service.doAdd("123", "456"));
    }

    @Test
    public void addError() throws Exception {
        // 예외 발생 테스트 (NumberFormatException)
        log.info("덧셈 결과 : " + service.doAdd("123", "ABC"));
    }
}