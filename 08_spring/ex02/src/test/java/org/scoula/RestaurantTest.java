package org.scoula;

import org.junit.jupiter.api.Test;                                    // JUnit 5 테스트 메서드 어노테이션
import org.junit.jupiter.api.extension.ExtendWith;                    // JUnit 5 확장 기능 등록
import org.scoula.config.RootConfig;                                  // Spring 설정 클래스 import
import org.springframework.beans.factory.annotation.Autowired;        // Spring 의존성 주입 어노테이션
import org.springframework.test.context.ContextConfiguration;         // Spring 테스트 컨텍스트 설정
import org.springframework.test.context.junit.jupiter.SpringExtension; // Spring + JUnit 5 통합 확장
import lombok.extern.log4j.Log4j2;                                    // Lombok 로깅 어노테이션
import static org.junit.jupiter.api.Assertions.assertNotNull;         // JUnit 5 단언문 (정적 import)

@ExtendWith(SpringExtension.class) // JUnit 5에서 Spring TestContext Framework 사용 활성화
@ContextConfiguration(classes = {RootConfig.class}) // 테스트에서 사용할 Spring 설정 클래스 지정
@Log4j2 // Lombok을 통해 log 객체 자동 생성 (Log4j2 사용)
class RestaurantTest {

    @Autowired  // 테스트 대상 객체 Restaurant 타입 Bean 의존성 주입
    private Restaurant restaurant;

    @Test  // JUnit 5 테스트 메서드임을 표시
    void getChef() {

        assertNotNull(restaurant); // restaurant 객체가 null이 아닌지 검증 (의존성 주입 성공 여부 확인)

        log.info(restaurant); // restaurant 객체 정보 로그 출력 (toString() 결과)
        log.info("------------------------------");
        log.info(restaurant.getChef());  // restaurant 내부의 chef 객체 정보 로그 출력

        // ===== 이 테스트가 검증하는 내용들 =====
        // 1. Spring 컨테이너가 올바르게 초기화되는가?
        // 2. Restaurant 빈이 정상적으로 생성되는가?
        // 3. Restaurant 내부의 Chef 의존성이 올바르게 주입되는가?
        // 4. @Autowired 어노테이션이 정상 동작하는가?
    }
}
