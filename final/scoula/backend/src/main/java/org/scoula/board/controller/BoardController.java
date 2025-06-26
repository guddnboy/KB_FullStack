package org.scoula.board.controller;


import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                    // REST API 컨트롤러 선언 (@Controller + @ResponseBody)
@RequestMapping("/api/board")   // 기본 URL 매핑
@RequiredArgsConstructor           // final 필드 생성자 자동 생성
@Slf4j                             // 로깅 기능
@Api(
        tags = "게시글 관리",                    // 그룹 이름 (필수)
        description = "게시판 CRUD API",        // 상세 설명
        value = "BoardController"              // 컨트롤러 식별자
)
public class BoardController {

    private final BoardService service; // 의존성 주입

    /**
     * 전체 목록 조회
     * GET: http://localhost:8080/api/board
     * @return ResponseEntity
     *         - 200 OK: 목록 조회 성공, 게시글 리스트 반환 (빈 리스트 포함)
     *         - 204 No Content: 조회 성공했지만 게시글이 하나도 없음
     *         - 500 Internal Server Error: 서버 내부 오류 (DB 연결 실패 등)
     */
    @ApiOperation(value = "게시글 목록 조회", notes = "게시글 목록을 얻는 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로 요청이 처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 500, message = "서버에서 오류가 발생했습니다.")
    })
    @GetMapping("")
    public ResponseEntity<List<BoardDTO>> getList() {
        log.info("============> 게시글 전체 목록 조회");

        List<BoardDTO> list = service.getList();
        return ResponseEntity.ok(list); // 200 OK + 데이터 반환
    }

    /**
     * 개별 게시글 조회
     * GET: http://localhost:8080/api/board/{no}
     * @param no 게시글 번호(PK)
     * @return ResponseEntity
     *         - 200 OK: 게시글 조회 성공, 게시글 정보 반환
     *         - 404 Not Found: 해당 번호의 게시글이 존재하지 않음
     *         - 400 Bad Request: 잘못된 게시글 번호 형식 (음수, 문자 등)
     *         - 500 Internal Server Error: 서버 내부 오류
     */
    @ApiOperation(value = "상세정보 얻기", notes = "게시글 상세 정보를 얻는 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로 요청이 처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 404, message = "게시글을 찾을 수 없습니다."),
            @ApiResponse(code = 500, message = "서버에서 오류가 발생했습니다.")
    })
    @GetMapping("/{no}")
    public ResponseEntity<BoardDTO> get(
            @ApiParam(
                    value = "게시글 ID",           // 매개변수 설명
                    required = true,              // 필수 여부
                    example = "1"                 // 예시 값
            )
            @PathVariable Long no) {
        log.info("============> 게시글 상세 조회: " + no);

        BoardDTO board = service.get(no);
        return ResponseEntity.ok(board);
    }



    /**
     * 새 게시글 작성
     * POST: http://localhost:8080/api/board
     * @param board 작성할 게시글 데이터 (제목, 내용, 작성자 필수)
     * @return ResponseEntity
     *         - 200 OK: 게시글 생성 성공 시 생성된 게시글 데이터 반환
     *         - 400 Bad Request: 잘못된 요청 데이터 (제목/내용 누락 등)
     *         - 500 Internal Server Error: 서버 내부 오류 발생 시
     */
    @ApiOperation(value = "게시글 생성", notes = "새로운 게시글을 생성하는 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로 요청이 처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 500, message = "서버에서 오류가 발생했습니다.")
    })
    @PostMapping("")
    public ResponseEntity<BoardDTO> create(
            @ApiParam(value = "게시글 객체", required = true)
            @RequestBody BoardDTO board) {
        log.info("============> 게시글 생성: " + board);

        // 새 게시글 생성 후 결과 반환
        BoardDTO createdBoard = service.create(board);
        return ResponseEntity.ok(createdBoard);
    }


    /**
     * 게시글 수정
     * PUT: http://localhost:8080/api/board/{no}
     * @param no 수정할 게시글 번호(PK)
     * @param board 수정할 게시글 데이터 (제목, 내용 등)
     * @return ResponseEntity<BoardDTO>
     *         - 200 OK: 게시글 수정 성공 시 수정된 게시글 데이터 반환
     *         - 400 Bad Request: 잘못된 요청 데이터 (제목/내용 누락, 잘못된 번호 형식 등)
     *         - 404 Not Found: 수정할 게시글이 존재하지 않음
     *         - 500 Internal Server Error: 서버 내부 오류 발생 시
     */
    @ApiOperation(value = "게시글 수정", notes = "기존 게시글을 수정하는 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로 요청이 처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 404, message = "게시글을 찾을 수 없습니다."),
            @ApiResponse(code = 500, message = "서버에서 오류가 발생했습니다.")
    })
    @PutMapping("/{no}")
    public ResponseEntity<BoardDTO> update(
            @ApiParam(value = "게시글 ID", required = true, example = "1")
            @PathVariable Long no,           // URL에서 게시글 번호 추출
            @ApiParam(value = "게시글 객체", required = true)
            @RequestBody BoardDTO board      // 수정할 데이터 (JSON)
    ) {
        log.info("============> 게시글 수정: " + no + ", " + board);

        // 게시글 번호 설정 (안전성을 위해)
        board.setNo(no);
        BoardDTO updatedBoard = service.update(board);
        return ResponseEntity.ok(updatedBoard);
    }

    /**
     * 게시글 삭제
     * DELETE: http://localhost:8080/api/board/{no}
     * @param no 삭제할 게시글 번호(PK)
     * @return ResponseEntity<BoardDTO>
     *         - 200 OK: 게시글 삭제 성공 시 삭제된 게시글 데이터 반환
     *         - 204 No Content: 게시글 삭제 성공 (응답 데이터 불필요한 경우)
     *         - 400 Bad Request: 잘못된 게시글 번호 형식 (음수, 문자 등)
     *         - 404 Not Found: 삭제할 게시글이 존재하지 않음
     *         - 403 Forbidden: 게시글 삭제 권한이 없음 (다른 사용자의 게시글 등)
     *         - 500 Internal Server Error: 서버 내부 오류 발생 시
     */
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제하는 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로 요청이 처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 404, message = "게시글을 찾을 수 없습니다."),
            @ApiResponse(code = 500, message = "서버에서 오류가 발생했습니다.")
    })
    @DeleteMapping("/{no}")
    public ResponseEntity<BoardDTO> delete(
            @ApiParam(value = "게시글 ID", required = true, example = "1")
            @PathVariable Long no) {
        log.info("============> 게시글 삭제: " + no);

        // 삭제된 게시글 정보를 반환
        BoardDTO deletedBoard = service.delete(no);
        return ResponseEntity.ok(deletedBoard);
    }
}
