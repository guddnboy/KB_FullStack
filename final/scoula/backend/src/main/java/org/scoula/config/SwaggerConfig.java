package org.scoula.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Configuration
@EnableSwagger2  // Swagger2 활성화 어노테이션
public class SwaggerConfig {

    /**
     * Swagger Docket Bean 정의
     * - Swagger 설정의 핵심 객체
     * - 어떤 API를 문서화할지, 보안 설정은 어떻게 할지를 정의함
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                // Swagger에 JWT 보안 설정 추가
                .securityContexts(List.of(this.securityContext()))  // 어떤 API 경로에 인증이 필요한지 설정
                .securitySchemes(List.of(this.apiKey()))           // JWT 토큰을 전달할 방식 정의 (header 방식)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))  // @RestController 붙은 클래스만 문서화 대상
                .paths(PathSelectors.any())  // 모든 경로 포함
                .build()
                .apiInfo(apiInfo());         // 문서 상단에 표시할 API 정보
    }

    /**
     * Swagger에서 사용할 SecurityContext 정의
     * - 어떤 API 요청에 보안 스키마를 적용할지 결정
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())  // 어떤 SecurityReference를 쓸지 지정
                .build();
    }

    /**
     * SecurityReference 정의
     * - 보안 설정의 이름(Authorization)과 범위(global)을 지정
     * - 여러 보안 스키마가 필요하면 List로 추가 가능
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        // "Authorization" 이라는 이름으로 JWT 토큰을 header에 보내겠다는 설정
        return List.of(new SecurityReference("Authorization", authorizationScopes));
    }

    /**
     * API Key 방식의 인증 정의
     * - Swagger UI 상단에 Authorization 헤더를 추가할 수 있는 입력창 생성
     * - "header" 방식으로 JWT 토큰을 보낸다는 의미
     */
    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    /**
     * (선택) Swagger UI에 표시할 API 문서 정보 설정
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("My API 문서")
                .description("JWT 인증이 적용된 Swagger UI입니다.")
                .version("1.0.0")
                .build();
    }
}
