/* root 계정*/
CREATE DATABASE jdbc_ex;
-- scoula계정에 scoula_db 모든 권한 부여
GRANT ALL PRIVILEGES ON jdbc_ex.* TO 'scoula'@'%';
-- 권한 적용
FLUSH PRIVILEGES;


/* scoula 계정 접속*/
-- jdbc_ex 데이터베이스 사용
USE jdbc_ex;

-- users 테이블이 존재하면 삭제
DROP TABLE IF EXISTS users;

-- users 테이블 생성
CREATE TABLE users
(
    id       VARCHAR(12) NOT NULL PRIMARY KEY,
    password VARCHAR(12) NOT NULL,
    name     VARCHAR(30) NOT NULL,
    role     VARCHAR(6)  NOT NULL
);

-- 샘플 데이터 추가
INSERT INTO USERS(ID, PASSWORD, NAME, ROLE)
VALUES ('guest', 'guest123', '방문자', 'USER');
INSERT INTO USERS(ID, PASSWORD, NAME, ROLE)
VALUES ('admin', 'admin123', '관리자', 'ADMIN');
INSERT INTO USERS(ID, PASSWORD, NAME, ROLE)
VALUES ('member', 'member123', '일반회원', 'USER');

-- 추가 확인
SELECT * FROM USERS;