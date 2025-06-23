package org.scoula.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/security")
@Controller
public class SecurityController {

    @GetMapping("/all")      // 모든 사용자 접근 가능
    public void doAll() {
        log.info("do all can access everybody");
    }

    @GetMapping("/member")   // MEMBER 또는 ADMIN 권한 필요
    public void doMember() {
        log.info("logined member");
    }

    @GetMapping("/admin")    // ADMIN 권한만 접근 가능
    public void doAdmin() {
        log.info("admin only");
    }


    @GetMapping("/login")    // 로그인 요청 매핑
    public void login() {
        log.info("login page");
    }

    @GetMapping("/logout")
    public void logout() {
        log.info("logout page");
    }
}
