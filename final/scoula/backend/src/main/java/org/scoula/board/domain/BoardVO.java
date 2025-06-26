package org.scoula.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data                    // getter, setter, toString 자동 생성
@NoArgsConstructor       // 기본 생성자 생성
@AllArgsConstructor      // 모든 필드 생성자 생성
@Builder                 // 빌더 패턴 적용
public class BoardVO {
    private Long no;           // 게시글 번호
    private String title;      // 제목
    private String content;    // 내용
    private String writer;     // 작성자
    private Date regDate;      // 등록일시
    private Date updateDate;   // 수정일시

    // 첨부파일 목록 추가
    private List<BoardAttachmentVO> attaches;
}