package org.scoula.board.service;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import java.util.List;

public interface BoardService {
    /**
     * 게시글 목록 조회
     * @return 게시글 DTO 목록
     */
    public List<BoardDTO> getList();

    /**
     * 특정 게시글 조회
     * @param no 게시글 번호
     * @return 게시글 DTO
     */
    public BoardDTO get(Long no);

    /* *** CUD 메서드 - 처리된 객체를 반환하도록 변경 *** */
    /**
     * 게시글 등록
     * @param board 등록할 게시글 DTO
     * @return 삽입 처리된 객체를 반환
     */
    public BoardDTO create(BoardDTO board);    // 생성된 객체 반환

    /**
     * 게시글 수정
     * @param board 수정할 게시글 DTO
     * @return 수정 처리된 객체를 반환
     */
    public BoardDTO update(BoardDTO board);

    /**
     * 게시글 삭제
     * @param no 삭제할 게시글 번호
     * @return 삭제 성공 여부
     */
    public BoardDTO delete(Long no);
    /* *** CUD 메서드 - 처리된 객체를 반환하도록 변경 *** */


    // 첨부파일 관련 메서드 추가
    public BoardAttachmentVO getAttachment(Long no);
    public boolean deleteAttachment(Long no);
}
