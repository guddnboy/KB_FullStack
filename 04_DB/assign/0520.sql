use sqldb;

drop table if exists tbl1;

create table tbl1(
    a int primary key ,
    b int,
    c int
);

show index from tbl1;

drop table if exists tbl2;
create table tbl2(
    a int primary key ,
    b int unique ,
    c int unique ,
    d int
);

show index from tbl2;

drop table if exists tbl3;

create table tbl3(
    a int unique ,
    b int unique ,
    c int unique ,
    d int
);

show index from tbl3;

drop table if exists tbl4;

create table tbl4(
    a int unique not null ,
    b int unique ,
    c int unique ,
    d int
);

show index from tbl4;

drop table if exists tbl5;

create table tbl5(
    a int unique not null ,
    b int unique ,
    c int unique ,
    d int primary key
);

show index from tbl5;

drop table if exists buytbl;
drop table if exists usertbl;

create table usertbl(
    userID char(8) not null primary key ,
    name varchar(10) not null ,
    birthYear int not null ,
    addr nchar(2) not null
);

insert into usertbl values ('LSG','이승기','1987','서울');
insert into usertbl values ('KBS','김범수','1979','경남');
insert into usertbl values ('KKH','김경호','1971','전남');
insert into usertbl values ('JYP','조용필','1950','경기');
insert into usertbl values ('SSK','성시경','1979','서울');

select * from usertbl;
show index from usertbl;

alter table usertbl drop primary key;

alter table usertbl add constraint pk_name primary key(name);

select * from usertbl;
show index from usertbl;

