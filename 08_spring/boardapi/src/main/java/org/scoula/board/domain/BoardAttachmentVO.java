package org.scoula.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.common.util.UploadFiles;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardAttachmentVO {
    private Long no;                // 첨부파일 고유 번호
    private Long bno;               // 게시글 번호 (FK)
    private String filename;        // 원본 파일명
    private String path;            // 서버 저장 경로
    private String contentType;     // MIME 타입
    private Long size;              // 파일 크기
    private Date regDate;           // 등록일

    /**
     * MultipartFile로부터 BoardAttachmentVO 객체를 생성하는 팩토리 메서드
     * @param part 업로드된 파일 객체
     * @param bno 게시글 번호
     * @param path 저장된 파일 경로
     * @return BoardAttachmentVO 객체
     */
    public static BoardAttachmentVO of(MultipartFile part, Long bno, String path) {
        return builder()
                .bno(bno)
                .filename(part.getOriginalFilename())     // 원본 파일명 저장
                .path(path)                               // 서버 저장 경로
                .contentType(part.getContentType())       // MIME 타입
                .size(part.getSize())                     // 파일 크기
                .build();
    }

    /**
     * 파일 크기를 사용자 친화적 형태로 변환
     * @return 포맷된 파일 크기 (예: 1.2 MB)
     */
    public String getFileSize() {
        return UploadFiles.getFormatSize(size);
    }
}
