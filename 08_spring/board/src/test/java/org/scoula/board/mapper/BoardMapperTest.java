package org.scoula.board.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    @DisplayName("BoardMapper의 목록 불러오기")
    public void getList() {
        for(BoardVO board : boardMapper.getList()) {
            log.info(board);
        }
    }

    @Test
    @DisplayName("BoardMapper의 게시글 읽기")
    public void get() {
        // 존재하는 게시물 번호로 테스트
        BoardVO board = boardMapper.get(1L);

        assertNotNull(board);  // 조회 결과가 null이 아닌지 확인
        assertEquals(1L, board.getNo());  // 번호 일치 확인

        log.info("조회된 게시글: {}", board);
    }

    @Test
    @DisplayName("BoardMapper의 새글 작성")
    public void create() {
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("user0");

        boardMapper.create(board);

        // 생성된 PK 값이 설정되었는지 확인
        log.info("생성된 게시글: {}", board);
        log.info("생성된 게시글 번호: {}", board.getNo());
    }

    @Test
    @DisplayName("BoardMapper의 글 수정")
    public void update() {
        BoardVO board = new BoardVO();
        board.setNo(5L);                    // 수정할 게시글 번호
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");
        board.setWriter("user00");

        int count = boardMapper.update(board);   // 수정된 행의 개수 반환

        assertEquals(1, count);             // 1개 행이 수정되었는지 확인
        log.info("UPDATE COUNT: {}", count);
    }

    @Test
    @DisplayName("BoardMapper의 글 삭제")
    public void delete() {
        int count = boardMapper.delete(3L);      // 삭제할 게시글 번호

        assertEquals(1, count);             // 1개 행이 삭제되었는지 확인
        log.info("DELETE COUNT: {}", count);

        // 삭제 후 조회 시 null 반환 확인
        BoardVO deletedBoard = boardMapper.get(3L);
        assertNull(deletedBoard);
    }
}