-- 기존 테이블이 있다면 삭제
DROP TABLE IF EXISTS tbl_board_attachment;

-- 첨부파일 테이블 생성
CREATE TABLE tbl_board_attachment
(
    no           INTEGER AUTO_INCREMENT PRIMARY KEY, -- 첨부파일 고유 번호
    filename     VARCHAR(256) NOT NULL,              -- 원본 파일명 (다운로드용)
    path         VARCHAR(256) NOT NULL,              -- 서버 저장 경로
    content_type VARCHAR(56),                        -- MIME 타입 (파일 형식 식별)
    size         INTEGER,                            -- 파일 크기 (바이트)
    bno          INTEGER      NOT NULL,              -- 게시글 번호 (외래키)
    reg_date     DATETIME DEFAULT now(),             -- 등록 시간

    -- 외래키 제약조건 설정
    CONSTRAINT FOREIGN KEY (bno) REFERENCES tbl_board (no)
);