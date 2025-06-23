package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;



/**
 * Spring Security 메인 설정 클래스
 *
 * 주요 기능:
 * - 데이터베이스 기반 사용자 인증
 * - 커스텀 로그인/로그아웃 페이지
 * - 경로별 접근 권한 제어
 * - 한글 문자 인코딩 처리
 * - BCrypt 비밀번호 암호화
 */
@Configuration
@EnableWebSecurity  // Spring Security 활성화
@Slf4j
@MapperScan(basePackages = {"org.scoula.security.account.mapper"})  // 매퍼 스캔 설정
@ComponentScan(basePackages = {"org.scoula.security"})    // 서비스 클래스 스캔
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;   // CustomUserDetailsService 주입


    /**
     * 비밀번호 암호화기 Bean 등록
     * BCrypt 해시 함수를 사용하여 안전한 비밀번호 저장
     *
     * @return BCryptPasswordEncoder 인스턴스
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCrypt 해시 함수 사용
    }


    /**
     * 한글 문자 인코딩 필터 생성
     * POST 요청시 한글 깨짐 현상 방지
     * Spring Security Filter Chain에서 CsrfFilter보다 먼저 실행되어야 함
     *
     * @return CharacterEncodingFilter 인스턴스
     */
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");           // UTF-8 인코딩 설정
        encodingFilter.setForceEncoding(true);         // 강제 인코딩 적용
        return encodingFilter;
    }

    /**
     * HTTP 보안 설정 메서드
     * 웹 애플리케이션의 보안 정책을 상세하게 구성
     * (어떤 URL에 누가 접근할 수 있고, 로그인/로그아웃을 어떻게 처리할지 설정하는 메서드)
     * @param http HttpSecurity 객체
     * @throws Exception 설정 중 발생할 수 있는 예외
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // // 1. 문자 인코딩 필터를 CSRF 필터보다 먼저 실행
        // CSRF 필터보다 앞에 인코딩 필터 추가
        // - CSRF 필터는 Spring Security 환경에서 기본적으로 활성화 되어있음!
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);

        // 2. 경로별 접근 권한 설정
        http.authorizeRequests()
                // 모든 사용자 허용
                .antMatchers("/security/all").permitAll()

                // ADMIN만 허용
                .antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')")

                // MEMBER, ADMIN 중 하나라도 만족하면 허용
                .antMatchers("/security/member").access("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')")

                // 로그인 사용자에게 허용 (예시)
                .antMatchers("/board/write", "/board/modify", "/board/delete").authenticated();


        // 3. 커스텀 로그인 설정
        // 폼 기반 로그인 활성화 (모든 설정이 기본값)
        http.formLogin()
                // 커스텀 로그인 페이지 설정 (Security 기본 로그인 사용 X, 사용자가 만든 로그인 페이지 사용!)
                .loginPage("/security/login")           // 로그인 폼 GET 요청 URL
                .loginProcessingUrl("/security/login")  // 로그인 처리 POST 요청 URL
                .defaultSuccessUrl("/");                // 로그인 성공 시 리다이렉트 URL


        // 4. 로그아웃 설정
        http.logout()
                .logoutUrl("/security/logout")  // POST: 로그아웃 요청 URL
                .invalidateHttpSession(true)    // 세션 무효화
                .deleteCookies("remember-me", "JSESSION-ID")      // 쿠키 삭제
                .logoutSuccessUrl("/security/logout");  // GET: 로그아웃 완료 후 이동할 페이지
    }



    /**
     * 인증 관리자 설정 메서드
     * 사용자 인증 방식과 비밀번호 암호화 방식을 설정
     * (Spring Security에서 인증 방식과 사용자 정보를 어떻게 처리할지 정의)

     * @param auth AuthenticationManagerBuilder 객체
     * @throws Exception 설정 중 발생할 수 있는 예외
     */
    //
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("configure .........................................");

        // UserDetailsService와 PasswordEncoder 설정
        auth.userDetailsService(userDetailsService)         // 커스텀 서비스 사용
                .passwordEncoder(passwordEncoder());        // BCrypt 암호화 사용
    }

}
