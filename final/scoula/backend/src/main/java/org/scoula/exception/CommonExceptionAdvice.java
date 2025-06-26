package org.scoula.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Slf4j
@Order(1)  // 우선순위 설정
public class CommonExceptionAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException ex) {
        // 404 에러 시 index.html로 포워딩 (SPA 라우팅 지원)
        return "/resources/index.html";
    }
}
