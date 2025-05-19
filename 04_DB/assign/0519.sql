drop database if exists tabledb;

create database tabledb;

use tabledb;

create table usertbl(
    userID      CHAR(8) NOT NULL PRIMARY KEY,
    name        VARCHAR(10) NOT NULL,
    birthYear   INT NOT NULL,
    addr        CHAR(2) NOT NULL,
    mobile1     CHAR(3) NULL,
    mobile2     CHAR(8) NULL,
    height      SMALLINT NULL,
    mDate       DATE NULL
);

create table buytbl(
    num         INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    userid      CHAR(8) NOT NULL,
    prodName    CHAR(6) NOT NULL,
    groupName   CHAR(4) NULL,
    price       INT NOT NULL,
    amount      SMALLINT NOT NULL,
    FOREIGN KEY (userid) REFERENCES usertbl(userID)

);

insert into usertbl values ('LSG', '이승기', 1987, '서울', '011', '1111111', 182, '2008-8-8');
insert into usertbl values ('KBS', '김범수', 1979, '경남', '011', '2222222', 173, '2012-4-4');
insert into usertbl values ('KKH', '김경호', 1971, '전남', '019', '3333333', 177, '2007-7-7');


insert into buytbl values (NULL, 'KBS', '운동화', NULL, 30, 2);
insert into buytbl values (NULL, 'KBS', '노트북', '전자', 1000, 1);

# 참조하고 있는 usertbl의 userID에 'JYP'가 없기 떄문에 오류가 발생한다.
# insert into buytbl values (NULL, 'JYP', '모니터', '전자', 200, 1);

# drop table if exists buytbl;
# drop table if exists usertbl;

# create table usertbl(
#     userID char(8) not null,
#     name varchar(10) not null,
#     birthyear int not null,
#     constraint primary key pk_usertbl_userid(userID)
# );

drop table if exists prodTbl;

create table prodTbl(
    prodCode char(3) not null ,
    prodID char(4) not null ,
    prodDate Datetime not null ,
    proCur char(10) null,
    CONSTRAINT pk_code_id
        PRIMARY KEY (prodCode, prodID)
);


drop view if exists v_usertbl;

CREATE VIEW v_userbuytbl
AS
SELECT
    U.userid,
    U.name,
    B.prodName,
    U.addr,
    CONCAT(U.mobile1, U.mobile2) AS '연락처'
FROM
    usertbl U
        INNER JOIN
    buytbl B ON U.userid = B.userid;

SELECT *
FROM
    v_userbuytbl
WHERE
    name = '김범수';