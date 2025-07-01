package org.scoula.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc  // Spring MVC 기능 활성화 - 기본 핸들러 매핑, 뷰 리졸버 등 자동 설정
@ComponentScan(basePackages = {
        "org.scoula.controller",      // 공통 컨트롤러 패키지
        "org.scoula.ex03.controller"  // 프로젝트별 컨트롤러 패키지
})
public class ServletConfig implements WebMvcConfigurer {

    // ViewResolver 설정 - 논리적 뷰 이름을 실제 JSP 파일 경로로 변환
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);        // JSTL 지원 활성화
        resolver.setPrefix("/WEB-INF/views/");        // JSP 파일 위치 접두사
        resolver.setSuffix(".jsp");                   // JSP 확장자 접미사
        registry.viewResolver(resolver);

        // 예: "home" → "/WEB-INF/views/home.jsp"로 변환
    }

    // 정적 리소스 핸들러 설정 - CSS, JS, 이미지 등 정적 파일 처리
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")  // URL 패턴
                .addResourceLocations("/resources/");  // 실제 파일 위치
    }
}
