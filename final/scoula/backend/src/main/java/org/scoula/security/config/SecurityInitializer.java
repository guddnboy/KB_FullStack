package org.scoula.security.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;

/**
 * Spring Security 필터 체인을 등록하는 초기화 클래스
 * 웹 애플리케이션 시작 시 자동으로 Security 필터들을 등록
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    // 문자셋 필터 설정
    private CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        // Security Filter 이전에 인코딩 필터와 Multipart 필터 추가
        insertFilters(servletContext, encodingFilter(), new MultipartFilter());
    }
}