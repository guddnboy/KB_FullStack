package org.scoula.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * 🌐 Spring MVC Web Context 설정 클래스
 * - Spring MVC의 웹 계층(Presentation Layer)을 담당하는 컨텍스트 설정 클래스
 * - 사용자 요청 처리와 관련된 모든 웹 컴포넌트들을 관리하고 설정함
 */
@EnableWebMvc
@ComponentScan(basePackages = {
        "org.scoula.exception",
        "org.scoula.controller",
        "org.scoula.board.controller"
}) // Spring MVC용 컴포넌트 등록을 위한 스캔 패키지
public class ServletConfig implements WebMvcConfigurer {

    /**
     * "/" 요청 시 /resources/index.html로 포워드 설정
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:/resources/index.html");
    }

    /**
     * 📁 정적 자원 핸들러 설정
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /resources/** 경로를 위한 핸들러 추가
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");

        // 프론트엔드 assets 핸들러
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("/resources/assets/");

        // Swagger UI 리소스 핸들러
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/v2/api-docs")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    // 📍 Servlet 3.0 파일 업로드 설정
    @Bean
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver resolver =
                new StandardServletMultipartResolver();
        return resolver;
    }
}
