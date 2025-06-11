package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.ex03.dto.SampleDTOList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@Controller                    // Spring MVC 컨트롤러임을 선언 - 컴포넌트 스캔 대상
@RequestMapping("/sample")     // 클래스 레벨 - 모든 메서드의 기본 URL 경로 설정
@Log4j2                        // 로깅을 위한 Lombok 어노테이션
public class SampleController {

    @RequestMapping("")        // 메서드 레벨 - 세부 경로 ("" = 기본 경로만 사용)
    public void basic() {      // void 리턴 = RequestMapping과 동일한 이름의 JSP 자동 매핑
        log.info("basic............");
        // /sample 요청 시 /WEB-INF/views/sample.jsp로 forward
    }
    // HTTP 메서드를 명시적으로 지정하는 방식 - 다중 메서드 허용
    @RequestMapping(value="/basic", method={RequestMethod.GET, RequestMethod.POST})
    public void basicGet() {
        log.info("basic get............");
        // GET과 POST 요청 모두 처리 가능
    }
    @GetMapping("/ex02")
    public String ex02(
            @RequestParam("name") String name,    // 파라미터명 "name"을 String으로 바인딩
            @RequestParam("age") int age) {       // 파라미터명 "age"를 int로 자동 변환
        log.info("name: " + name);
        log.info("age: " + age);
        return "ex02";
    }

    // @RequestParam 옵션 활용 - 파라미터 누락 및 기본값 처리
    @GetMapping("/ex02-advanced")
    public String ex02Advanced(
            @RequestParam(value="name", required=false, defaultValue="익명") String name,
            @RequestParam(value="age", required=false, defaultValue="10") int age) {
        // required=false: 파라미터가 없어도 에러 발생하지 않음
        // defaultValue: 파라미터가 없을 때 사용할 기본값 (문자열로 지정, 자동 형변환)
        return "ex02";
    }
    @GetMapping("/ex02List")
    public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
        log.info("ids: " + ids);
        return "ex02List";
        // 동일한 파라미터명으로 전송된 여러 값을 List로 자동 수집
    }
    @GetMapping("/ex02Array")
    public String ex02Array(@RequestParam("ids") String[] ids) {
        log.info("array ids: " + Arrays.toString(ids));
        return "ex02Array";
        // 동일한 파라미터명으로 전송된 여러 값을 배열로 자동 수집
    }

    @GetMapping("/ex02Bean")
    public String ex02Bean(SampleDTOList list) {
        log.info("list dtos: " + list);
        return "ex02Bean";
        // 복합 객체 내부의 리스트도 자동 바인딩 처리
    }
}