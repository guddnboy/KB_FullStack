package org.scoula.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {

    // 📍 일반 예외 처리
    @ExceptionHandler(Exception.class)
    public String except(Exception ex, Model model) {
        log.error("Exception: " + ex.getMessage());
        model.addAttribute("exception", ex);
        log.error(model);
        return "error_page";
    }

    // 📍 404 에러 전용 처리
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException ex,
                            HttpServletRequest request,
                            Model model) {
        log.error("404 Error: " + ex.getMessage());
        model.addAttribute("uri", request.getRequestURI());
        return "custom404";
    }
}
