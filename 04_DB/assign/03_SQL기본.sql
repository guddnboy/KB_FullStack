-- 2page
SHOW DATABASES;

USE employees;

SHOW TABLES ;

DESC employees;

-- 3page
SELECT * FROM titles;

SELECT first_name FROM employees;

SELECT first_name, last_name, gender FROM employees;

-- 4page
SELECT first_name AS 이름,
       last_name AS 성별,
       hire_date AS `회사 입사일`
FROM employees;

-- 6page
USE sqldb;

SELECT * FROM usertbl
         WHERE name = '김경호';

SELECT * FROM usertbl
         WHERE birthYear >= 1970 and height >= 182;

-- 7page
SELECT * FROM usertbl
         WHERE 180 <= height <= 183;

SELECT * FROM usertbl
         WHERE addr IN ('경남','경북');

SELECT * FROM usertbl WHERE name LIKE '김%';

-- 8page
SELECT name, height FROM usertbl
                    WHERE height > (SELECT height FROM usertbl WHERE name='김경호');

-- 9page
SELECT name, mDate FROM usertbl ORDER BY mDate;

SELECT name, mDate FROM usertbl ORDER BY mDate DESC;

SELECT name, height FROM usertbl ORDER BY height DESC, name DESC;

-- 10page
SElECT DISTINCT addr FROM usertbl ORDER BY addr;

-- 11page
USE world;
SHOW TABLES;
SELECT  * FROM city
          WHERE CountryCode = 'KOR'
          ORDER BY Population DESC;

SELECT CountryCode, Population FROM city
                               ORDER BY CountryCode, Population DESC;

SELECT COUNT(*) FROM city
                WHERE CountryCode = 'KOR';

SELECT * FROM city
         WHERE CountryCode in ('KOR', 'CHM', 'JPN');

SELECT * FROM city
         WHERE CountryCode = 'KOR' AND Population >= 1000000;

SELECT * FROM city
         WHERE CountryCode = 'KOR'
         ORDER BY Population DESC
         LIMIT 10;

SELECT * FROM city
         WHERE CountryCode = 'KOR'
           AND 1000000 <= Population <= 5000000;
