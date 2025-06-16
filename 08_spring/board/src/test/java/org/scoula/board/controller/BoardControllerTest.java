package org.scoula.board.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.service.BoardService;
import org.scoula.config.RootConfig;
import org.scoula.config.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;


@ExtendWith(SpringExtension.class)  // JUnit5와 Spring 통합
@WebAppConfiguration                // 웹 어플리케이션 컨텍스트 로드
@ContextConfiguration(classes = {   // 테스트에 사용할 설정 클래스 지정
        RootConfig.class,           // 비즈니스/영속 계층 설정
        ServletConfig.class,        // 웹 계층 설정
})
@Log4j2                            // 로깅 기능 활성화
class BoardControllerTest {

    // 비즈니스 서비스 계층 주입 (실제 빈 사용)
    @Autowired
    BoardService service;

    // 웹 어플리케이션 컨텍스트 주입 (MockMvc 생성용)
    @Autowired
    private WebApplicationContext ctx;

    // HTTP 요청을 시뮬레이션하는 가짜 MVC 객체
    private MockMvc mockMvc;

    // 각 테스트 실행 전 MockMvc 초기화
    @BeforeEach
    public void setup() {
        // 웹 컨텍스트를 이용해 MockMvc 객체 생성
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    // 목록 조회 테스트
    @Test
    public void list() throws Exception {
        ModelMap model = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                .andReturn()                    // MvcResult 반환
                .getModelAndView()             // ModelAndView 반환
                .getModelMap();                // Model 데이터 추출

        log.info(model);                       // 모델 데이터 로그 출력
    }

    // 동륵 테스트
    @Test
    public void postCreate() throws Exception {
        String resultPage = mockMvc.perform(
                        MockMvcRequestBuilders.post("/board/create")
                                .param("title", "테스트 새글 제목")      // 폼 파라미터 설정
                                .param("content", "테스트 새글 내용")
                                .param("writer", "user1")
                )
                .andReturn()
                .getModelAndView()
                .getViewName();                          // 리다이렉트 URL 확인

        log.info(resultPage);                    // "redirect:/board/list"
    }

    // 상세 조회 테스트
    @Test
    public void get() throws Exception {
        ModelMap model = mockMvc.perform(
                        MockMvcRequestBuilders.get("/board/get")
                                .param("no", "1")                // 쿼리 파라미터 설정
                )
                .andReturn()
                .getModelAndView()
                .getModelMap();

        log.info(model);                         // 조회된 게시글 데이터
    }

    // 수정 테스트
    @Test
    public void update() throws Exception {
        String resultPage = mockMvc.perform(
                        MockMvcRequestBuilders.post("/board/update")
                                .param("no", "1")                // 수정할 게시글 번호
                                .param("title", "수정된 테스트 새글 제목")
                                .param("content", "수정된 테스트 새글 내용")
                                .param("writer", "user00")
                )
                .andReturn()
                .getModelAndView()
                .getViewName();

        log.info(resultPage);
    }

    // 삭제 테스트
    @Test
    public void delete() throws Exception {
        String resultPage = mockMvc.perform(
                        MockMvcRequestBuilders.post("/board/delete")
                                .param("no", "25")               // 삭제할 게시글 번호
                )
                .andReturn()
                .getModelAndView()
                .getViewName();

        log.info(resultPage);
    }
}