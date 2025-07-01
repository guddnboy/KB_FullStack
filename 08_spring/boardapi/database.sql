-- 기존 테이블이 있다면 삭제
DROP TABLE IF EXISTS tbl_board_attachment;
DROP TABLE IF EXISTS tbl_board;

-- 게시판 테이블 생성
CREATE TABLE tbl_board
(
    no          INTEGER AUTO_INCREMENT PRIMARY KEY, -- 게시글 고유 번호
    title       VARCHAR(200) NOT NULL,              -- 제목
    content     TEXT NOT NULL,                      -- 내용
    writer      VARCHAR(50) NOT NULL,               -- 작성자
    reg_date    DATETIME DEFAULT now(),             -- 등록 시간
    update_date DATETIME DEFAULT now()              -- 수정 시간
);

-- 샘플 데이터 5개 추가
INSERT INTO tbl_board (title, content, writer) VALUES 
('첫 번째 게시글', '첫 번째 게시글의 내용입니다.', '홍길동'),
('두 번째 게시글', '두 번째 게시글의 내용입니다.', '김철수'),
('세 번째 게시글', '세 번째 게시글의 내용입니다.', '이영희'),
('네 번째 게시글', '네 번째 게시글의 내용입니다.', '박민수'),
('다섯 번째 게시글', '다섯 번째 게시글의 내용입니다.', '정지원');

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

    -- 외래키 제약조건 설정 + ON DELETE CASCADE
    CONSTRAINT fk_board_attachment_board
        FOREIGN KEY (bno)
            REFERENCES tbl_board (no)
            ON DELETE CASCADE
);
