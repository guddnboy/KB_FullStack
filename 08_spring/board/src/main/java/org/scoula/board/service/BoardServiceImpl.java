package org.scoula.board.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2                      // 로깅
@Service                     // Service 계층 컴포넌트
@RequiredArgsConstructor     // final 필드 생성자 주입
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;  // Mapper 의존성 주입

    // 목록 조회 서비스
    @Override
    public List<BoardDTO> getList() {
        log.info("getList..........");

        return boardMapper.getList().stream()    // List<BoardVO> → Stream<BoardVO>
                .map(BoardDTO::of)               // Stream<BoardVO> → Stream<BoardDTO>
                .toList();                       // Stream<BoardDTO> → List<BoardDTO>
    }

    // 단일 조회 서비스
    @Override
    public BoardDTO get(Long no) {
        log.info("get......" + no);

        BoardVO vo = boardMapper.get(no);               // DB에서 VO 조회
        BoardDTO dto = BoardDTO.of(vo);                 // VO → DTO 변환

        return Optional.ofNullable(dto)                 // null 안전성 처리
                .orElseThrow(NoSuchElementException::new);  // 없으면 예외 발생
    }

    // 게시글 등록 서비스
    @Override
    public void create(BoardDTO board) {
        log.info("create......" + board);

        BoardVO vo = board.toVo();         // DTO → VO 변환
        boardMapper.create(vo);            // DB에 저장
        board.setNo(vo.getNo());           // 생성된 PK를 DTO에 설정
    }

    // 게시글 수정 서비스
    @Override
    public boolean update(BoardDTO board) {
        log.info("update......" + board);

        int affectedRows = boardMapper.update(board.toVo());  // 영향받은 행 수 반환
        return affectedRows == 1;                        // 1개 행이 수정되면 성공
    }

    // 게시글 삭제 서비스
    @Override
    public boolean delete(Long no) {
        log.info("delete...." + no);

        int affectedRows = boardMapper.delete(no);     // 삭제된 행 수 반환
        return affectedRows == 1;                 // 1개 행이 삭제되면 성공
    }

}