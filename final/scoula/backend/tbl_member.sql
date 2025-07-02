-- 기존 테이블 삭제
drop table if exists tbl_member_auth;
drop table if exists tbl_member;

-- 사용자 기본 정보 테이블
create table tbl_member
(
    username    varchar(50) primary key, -- 사용자 ID (기본키)
    password    varchar(128) not null,   -- 암호화된 비밀번호 (BCrypt)
    email       varchar(50)  not null,   -- 이메일 주소
    reg_date    datetime default now(),  -- 등록일시
    update_date datetime default now()   -- 수정일시
);


-- 사용자 권한 정보 테이블
create table tbl_member_auth
(
    username varchar(50) not null, -- 사용자 ID (외래키)
    auth     varchar(50) not null, -- 권한 문자열 (ROLE_ADMIN, ROLE_MANAGER, ROLE_MEMBER 등)
    primary key (username, auth),  -- 복합 기본키
    constraint fk_authorities_users foreign key (username) references tbl_member (username)
);

-- 테스트 사용자 추가 (비밀번호: 1234)
insert into tbl_member(username, password, email)
values
    ('admin',  '$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC', 'admin@galapgos.org'),
    ('user0',  '$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC', 'user0@galapgos.org'),
    ('user1',  '$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC', 'user1@galapgos.org'),
    ('user2',  '$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC', 'user2@galapgos.org'),
    ('user3',  '$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC', 'user3@galapgos.org'),
    ('user4',  '$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC', 'user4@galapgos.org');


-- 권한 데이터 추가
insert into tbl_member_auth(username, auth)
values
-- ADMIN (최고관리자): 모든 권한 보유
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_MANAGER'),
('admin', 'ROLE_MEMBER'),

-- MANAGER (일반관리자): 관리자 + 일반회원 권한
('user0', 'ROLE_MANAGER'),
('user0', 'ROLE_MEMBER'),

-- MEMBER (일반회원): 기본 권한만
('user1', 'ROLE_MEMBER'),
('user2', 'ROLE_MEMBER'),
('user3', 'ROLE_MEMBER'),
('user4', 'ROLE_MEMBER');

DROP TABLE IF EXISTS tbl_board_attachment;

CREATE TABLE tbl_board (
    no INTEGER AUTO_INCREMENT PRIMARY KEY,          -- 게시글 고유 번호
    title VARCHAR(256) NOT NULL,                    -- 게시글 제목
    content TEXT NOT NULL,                           -- 게시글 내용
    writer VARCHAR(50) NOT NULL,                    -- 작성자 (사용자 ID)
    reg_date DATETIME DEFAULT now(),                -- 등록 시간
    update_date DATETIME DEFAULT now() ON UPDATE now(), -- 수정 시간
    view_count INTEGER DEFAULT 0,                   -- 조회수
    secret BOOLEAN DEFAULT FALSE,                   -- 비밀글 여부

    -- 외래키 제약조건: 작성자가 존재하는 사용자여야 함
    CONSTRAINT FOREIGN KEY (writer) REFERENCES tbl_member (username)
);

CREATE TABLE tbl_board_attachment (
                                      no INTEGER AUTO_INCREMENT PRIMARY KEY,     -- 첨부파일 고유 번호
                                      filename VARCHAR(256) NOT NULL,            -- 원본 파일명
                                      path VARCHAR(256) NOT NULL,                -- 서버 저장 경로
                                      content_type VARCHAR(56),                  -- MIME 타입
                                      size INTEGER,                              -- 파일 크기 (바이트)
                                      bno INTEGER NOT NULL,                      -- 게시글 번호 (FK)
                                      reg_date DATETIME DEFAULT now(),           -- 등록 시간

    -- 외래키 제약조건: 게시글 삭제 시 첨부파일도 함께 삭제
                                      CONSTRAINT FOREIGN KEY (bno) REFERENCES tbl_board (no) ON DELETE CASCADE
);