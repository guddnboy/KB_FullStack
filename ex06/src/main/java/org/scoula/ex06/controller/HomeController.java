package org.scoula.ex06.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 주요 특징:
 * - 애플리케이션의 메인 페이지 처리
 * - Command Pattern에서 실제 명령을 수행하는 역할
 * - 메서드 시그니처가 Command 인터페이스와 일치하여 메서드 참조 가능
 *
 * Command Pattern 적용:
 * - getIndex 메서드가 Command.execute()와 동일한 시그니처
 * - FrontControllerServlet에서 homeController::getIndex로 참조 가능
 *
 * 처리 흐름:
 * 1. 클라이언트가 "/" 요청
 * 2. FrontControllerServlet이 이 메서드를 Command로 실행
 * 3. "index" 뷰 이름 반환
 * 4. ViewResolver가 "/WEB-INF/views/index.jsp"로 변환
 * 5. JSP 페이지 포워드
 */
public class HomeController {
    public String getIndex(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return "index";
    }
}