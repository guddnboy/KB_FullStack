package org.scoula.board.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;

public interface BoardMapper {
    // === 게시글 CRUD ===
    List<BoardVO> getList();                    // 게시글 목록 조회
    BoardVO get(Long no);                       // 게시글 상세 조회 (첨부파일 포함)
    void create(BoardVO board);                 // 게시글 생성
    int update(BoardVO board);                  // 게시글 수정
    int delete(Long no);                        // 게시글 삭제

    // === 첨부파일 관리 ===
    void createAttachment(BoardAttachmentVO attach);           // 첨부파일 등록
    List<BoardAttachmentVO> getAttachmentList(Long bno);       // 게시글별 첨부파일 목록
    BoardAttachmentVO getAttachment(Long no);                  // 첨부파일 상세 조회
    int deleteAttachment(Long no);                             // 첨부파일 삭제
}