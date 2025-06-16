package org.scoula.board.service;

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

    /**
     * 게시글 등록
     * @param board 등록할 게시글 DTO
     */
    public void create(BoardDTO board);

    /**
     * 게시글 수정
     * @param board 수정할 게시글 DTO
     * @return 수정 성공 여부
     */
    public boolean update(BoardDTO board);

    /**
     * 게시글 삭제
     * @param no 삭제할 게시글 번호
     * @return 삭제 성공 여부
     */
    public boolean delete(Long no);
}
