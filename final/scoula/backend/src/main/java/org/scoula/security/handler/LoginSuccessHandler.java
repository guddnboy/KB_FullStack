package org.scoula.security.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scoula.security.account.domain.CustomUser;
import org.scoula.security.dto.AuthResultDTO;
import org.scoula.security.dto.UserInfoDTO;
import org.scoula.security.util.JsonResponse;
import org.scoula.security.util.JwtProcessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    // JWT 처리용 유틸리티 클래스 의존성 주입
    private final JwtProcessor jwtProcessor;

    /**
     * 인증 성공 결과 생성 메서드
     * @param user
     * @return AuthResultDTO
     */
    private AuthResultDTO makeAuthResult(CustomUser user) {
        String username = user.getUsername();

        // JWT 토큰 생성
        String token = jwtProcessor.generateToken(username);

        // 토큰 + 사용자 기본 정보를 AuthResultDTO로 구성
        return new AuthResultDTO(token, UserInfoDTO.of(user.getMember()));
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // 인증 결과에서 사용자 정보 추출
        CustomUser user = (CustomUser) authentication.getPrincipal();

        // 인증 성공 결과를 JSON으로 직접 응답
        AuthResultDTO result = makeAuthResult(user);
        JsonResponse.send(response, result);
    }
}
