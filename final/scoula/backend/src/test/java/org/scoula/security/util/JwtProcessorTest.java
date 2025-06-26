package org.scoula.security.util;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.scoula.config.RootConfig;
import org.scoula.security.config.SecurityConfig;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@Slf4j
class JwtProcessorTest {

    @Autowired
    JwtProcessor jwtProcessor;


    /* 토큰 생성 테스트 */
    @Test
    void generateToken() {

        // 테스트에 사용할 username
        String username = "user0";

        // username을 이용해 JWT 토큰 생성
        String token = jwtProcessor.generateToken(username);

        log.info("생성된 토큰: {}", token);
        assertNotNull(token);
        assertTrue(token.contains("."));  // JWT 구조 확인 (Header.Payload.Signature)

        // 토큰 구조 검증
        String[] parts = token.split("\\.");
        log.info("Header   : {}", parts[0]);
        log.info("Payload  : {}", parts[1]);
        log.info("Signature: {}", parts[2]);
        assertEquals(3, parts.length, "JWT는 3부분으로 구성되어야 합니다.");

        /* 토큰 생성에 사용된 SecretKey와 생성된 토큰을 알고 있으면 Token 내용을 Decoding 할 수 있음
        * JSON Web Token (JWT) Debugger 사이트 : https://jwt.io/
        * */
    }

    /* 사용자명 추출 테스트 */
    @Test
    void getUsername() {
        //  실제 테스트에서 생성된 토큰 사용
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDY3ODA2OCwiZXhwIjoxNzUwNjc4MzY4fQ.3a5iLHwO0dvg_QyaYn7ML5yn5kdsxh_uO88L_NQjjhU";

        // JWT Subject(username) 추출
        String username = jwtProcessor.getUsername(token);

        log.info("추출된 사용자명: {}", username);
        assertNotNull(username);
        assertEquals("user0", username);
    }



    /* 사용자명, 권한 추출 테스트*/
    @Test
    void generateTokenWithRole() {

        // username, role을 이용해 새로 생성한 토큰으로 테스트
        String username = "testUser";
        String role = "ADMIN";
        String token = jwtProcessor.generateTokenWithRole(username, role);

        log.info("새로 생성한 토큰: {}", token);


        String extractedUsername = jwtProcessor.getUsername(token);
        String extractedRole = jwtProcessor.getRole(token);
        log.info("새로 생성한 토큰에서 추출한 : {}", extractedUsername);
        log.info("새로 생성한 토큰에서 추출한 권한: {}", extractedRole);

        // Then
        assertEquals(username, extractedUsername);
    }

    /* 새로 생성한 토큰으로 테스트 */
    @Test
    void getUsernameFromFreshToken() {

        String username = "testUser";
        String token = jwtProcessor.generateToken(username);

        log.info("새로 생성한 토큰: {}", token);

        // When
        String extractedUsername = jwtProcessor.getUsername(token);
        log.info("새로 생성한 토큰에서 추출한 사용자명: {}", extractedUsername);

        // Then
        assertEquals(username, extractedUsername);
    }


    /* 토큰 검증 테스트 */
    @Test
    void validateToken_Valid() {
        // 새로 생성한 유효한 토큰
        String token = jwtProcessor.generateToken("testUser");

        // JWT 검증 (유효 기간 및 서명 검증)
        boolean isValid = jwtProcessor.validateToken(token);

        assertTrue(isValid, "새로 생성한 토큰은 유효해야 합니다.");
    }

    /* 5분 경과 후 테스트 (수동으로 만료된 토큰 사용) */
    @Test
    void validateToken_Expired() {
        String expiredToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDY3ODA2OCwiZXhwIjoxNzUwNjc4MzY4fQ.3a5iLHwO0dvg_QyaYn7ML5yn5kdsxh_uO88L_NQjjhU";


        assertThrows(ExpiredJwtException.class, () -> {
            jwtProcessor.getUsername(expiredToken);  // 만료된 토큰 사용 시 예외 발생
        });

        // 검증 메서드는 예외를 잡아서 false 반환
        boolean isValid = jwtProcessor.validateToken(expiredToken);
        assertFalse(isValid, "만료된 토큰은 무효해야 합니다.");
    }

    @Test
    void validateToken_Invalid() {
        // 잘못된 형식의 토큰
        String invalidToken = "invalid.jwt.token";

        boolean isValid = jwtProcessor.validateToken(invalidToken);

        assertFalse(isValid, "잘못된 형식의 토큰은 무효해야 합니다.");
    }


    /* 토큰 만료 시뮬레이션 */
    @Test
    void tokenExpiration_Simulation() throws InterruptedException {
        // Given - 매우 짧은 유효기간의 토큰 생성 (1초)
        // 실제로는 JwtProcessor에 테스트용 메서드 추가 필요
        String shortLivedToken = jwtProcessor.generateTokenWithExpiry("testUser", 3000L);

        // When - 토큰 생성 직후에는 유효
        assertTrue(jwtProcessor.validateToken(shortLivedToken));

        // 2초 대기
        Thread.sleep(3000);

        // Then - 만료 후에는 무효
        assertFalse(jwtProcessor.validateToken(shortLivedToken));
    }

}