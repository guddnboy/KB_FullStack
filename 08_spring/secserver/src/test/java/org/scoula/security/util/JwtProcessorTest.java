package org.scoula.security.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.security.config.SecurityConfig;
import org.scoula.security.util.JwtProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class, SecurityConfig.class })
@Log4j2
class JwtProcessorTest {
    @Autowired
    JwtProcessor jwtProcessor;
    @Test
    void generateToken() {
        String username = "user0";
        String token = jwtProcessor.generateToken(username);
        log.info(token);
        assertNotNull(token);
    }

    @Test
    void getUsername() {
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDgxOTk5NCwiZXhwIjoxNzUwODIwMjk0fQ.yx2PNoi_6EV2fudqUXcre40-i0otiEewEY89MgY81SjBmGNJC-zQkV-yd3DFhuR-";
        String username = jwtProcessor.getUsername(token);
        log.info(username);
        assertNotNull(username);
    }

    @Test
    void validateToken() {
// 5분 경과 후 테스트
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDgxOTk5NCwiZXhwIjoxNzUwODIwMjk0fQ.yx2PNoi_6EV2fudqUXcre40-i0otiEewEY89MgY81SjBmGNJC-zQkV-yd3DFhuR-";
        boolean isValid = jwtProcessor.validateToken(token); // 5분 경과 후면 예외 발생
        log.info(isValid);
        assertTrue(isValid); // 5분전이면 true
    }
}