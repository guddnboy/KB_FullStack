package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.scoula.security.filter.AuthenticationErrorFilter;
import org.scoula.security.filter.JwtAuthenticationFilter;
import org.scoula.security.filter.JwtUsernamePasswordAuthenticationFilter;
import org.scoula.security.handler.CustomAccessDeniedHandler;
import org.scoula.security.handler.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationErrorFilter authenticationErrorFilter;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter;

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

    // AuthenticationManager 빈 등록
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
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
        // 한글 인코딩 필터 설정
        http.addFilterBefore(encodingFilter(), CsrfFilter.class)
                // 인증 에러 필터
                .addFilterBefore(authenticationErrorFilter, UsernamePasswordAuthenticationFilter.class)
        // Jwt 인증 필터
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        // 로그인 인증 필터
                .addFilterBefore(jwtUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.httpBasic().disable() // 기본 HTTP 인증 비활성화
                .csrf().disable() // CSRF 비활성화
                .formLogin().disable() // formLogin 비활성화  관련 필터 해제
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션 생성 모드 설정

        // 예외 처리 설정
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        http
                .authorizeRequests() // 경로별 접근 권한 설정
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/api/security/all").permitAll() // 모두 허용
                .antMatchers("/api/security/member").access("hasRole('ROLE_MEMBER')") // ROLE_MEMBER 이상 접근 허용
                .antMatchers("/api/security/admin").access("hasRole('ROLE_ADMIN')") // ROLE_ADMIN 이상 접근 허용
                .anyRequest().authenticated(); // 나머지는 로그인 된 경우 모두 허용
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
