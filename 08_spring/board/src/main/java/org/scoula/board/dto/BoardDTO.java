package org.scoula.board.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.springframework.web.multipart.MultipartFile;

@Data                    // getter, setter, toString, equals, hashCode 생성
@NoArgsConstructor       // 기본 생성자
@AllArgsConstructor      // 모든 필드 생성자
@Builder
@ApiModel(description = "게시글 DTO")// 빌더 패턴
public class BoardDTO {
    @ApiModelProperty(value = "업로드파일목록")
    List<MultipartFile> files = new ArrayList<>(); //실제 업로드 된 파일(Multipart)목록

    @ApiModelProperty(value = "게시글 ID", example = "1")

    private Long no;
    @ApiModelProperty(value = "제목")

    private String title;
    @ApiModelProperty(value = "글본문")

    private String content;
    @ApiModelProperty(value = "작성자")

    private String writer;
    @ApiModelProperty(value = "등록일")

    private Date regDate;
    @ApiModelProperty(value = "수정일")

    private Date updateDate;
    //첨부파일
    @ApiModelProperty(value = "첨부파일목록")


    // 첨부파일 정보
    private List<BoardAttachmentVO> attaches;

    // VO → DTO 변환
    public static BoardDTO of(BoardVO vo) {
        return BoardDTO.builder()
                .no(vo.getNo())
                .title(vo.getTitle())
                .content(vo.getContent())
                .writer(vo.getWriter())
                .regDate(vo.getRegDate())
                .updateDate(vo.getUpdateDate())
                .build();
    }

    // DTO → VO 변환
    public BoardVO toVo() {
        return BoardVO.builder()
                .no(no)
                .title(title)
                .content(content)
                .writer(writer)
                .attaches(attaches)
                .regDate(regDate)
                .updateDate(updateDate)
                .build();
    }


}