package org.scoula.config;

import org.scoula.security.config.SecurityConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * 🚀 웹 애플리케이션 부트스트래핑 설정 클래스
 * - Spring MVC 웹 애플리케이션의 진입점 역할을 하는 클래스
 * - web.xml(배포 서술자)을 대체하여 서블릿 컨테이너 초기화와 Spring 컨텍스트 설정을 담당
 */
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {


    // 📍 파일 업로드 설정 상수
    final String LOCATION = "c:/upload";
    final long MAX_FILE_SIZE = 1024 * 1024 * 10L;      // 10MB
    final long MAX_REQUEST_SIZE = 1024 * 1024 * 20L;   // 20MB
    final int FILE_SIZE_THRESHOLD = 1024 * 1024 * 5;   // 5MB

    /**
     * 🌱 Root Application Context 설정 클래스 지정
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootConfig.class,
                SecurityConfig.class // <- 추가
        };
    }

    /**
     * 🌐 Servlet Application Context 설정 클래스 지정
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {

        return new Class[]{ServletConfig.class, SwaggerConfig.class};
    }

    /**
     * 🎯 DispatcherServlet URL 매핑 패턴 설정
     * - Spring MVC의 Front Controller인 DispatcherServlet이 처리할 URL 패턴 정의
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; // 스프링의 FrontController인 DispatcherServlet이 담당할 URL 매핑 패턴
    }

    /**
     * 🔧 서블릿 필터 설정 (HTTP 요청/응답 전처리)
     * - DispatcherServlet으로 전달되기 전에 HTTP 요청/응답을 전처리할 필터들을 등록하고 설정
     */
    protected Filter[] getServletFilters() {
        // UTF-8 문자 인코딩 필터 생성 및 설정
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");       // 요청 데이터 UTF-8 디코딩
        characterEncodingFilter.setForceEncoding(true);     // 응답 데이터도 UTF-8 강제 인코딩

        return new Filter[]{characterEncodingFilter};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // 📍 404 에러를 Exception으로 변환
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");

        // 📍 Multipart 파일 업로드 설정
        MultipartConfigElement multipartConfig = new MultipartConfigElement(
                LOCATION,           // 업로드 처리 디렉토리 경로
                MAX_FILE_SIZE,      // 업로드 가능한 파일 하나의 최대 크기
                MAX_REQUEST_SIZE,   // 업로드 가능한 전체 최대 크기(여러 파일 업로드)
                FILE_SIZE_THRESHOLD // 메모리 파일의 최대 크기(임계값)
        );
        registration.setMultipartConfig(multipartConfig);
    }
}
