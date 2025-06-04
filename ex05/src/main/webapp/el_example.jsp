<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>EL 객체, 연산자 예제</title>
</head>
<body>
<h1>EL 객체, 연산자 예제</h1>

<h2>1. 요청 파라미터 접근</h2>
<%-- <http://localhost:8080/el_example?id=testuser&pass=1234&address=서울시&address=광진구> --%>
<p>ID: ${param.id}</p>
<p>PASS: ${param.pass}</p>
<p>address: ${paramValues.address}</p>
<p>address[0]: ${paramValues.address[0]}</p>
<p>address:[1] ${paramValues.address[1]}</p>


<h2>2. 산술 연산</h2>
<p>10 + 5 = ${10 + 5}</p>
<p>20 / 3 = ${20 / 3}</p>
<p>10 % 3 = ${10 % 3}</p>

<h2>3. 관계 연산</h2>
<p>점수가 90 이상인가? ${score ge 90}</p>

<p>
    사용자 이름이 '홍길동'과 같은가? ${userName eq '홍길동'}
    <br>
    (EL은 문자열 비교도 ==(eq) 연산자 사용)
</p>

<h2>4. 논리 연산</h2>
<p>점수가 90 이상이고 이름이 홍길동인가? ${score ge 90 && userName eq '김국민'}</p>
<p>점수가 100이 아닌가? ${not (score ne 100)}</p>
<p>점수가 100이 아닌가? ${score eq 100}</p>

<h2>5. empty 연산자</h2>
<ul>
    <li>emptyVar : ${emptyVar}</li>
    <li>emptyString : ${emptyString}</li>
    <li>emptyList : ${emptyList}</li>
    <li>userName : ${userName}</li>
</ul>

<p>emptyVar 비었나? ${empty emptyVar}</p>
<p>emptyString 비었나? ${empty emptyString}</p>
<p>emptyList 비었나? ${empty emptyList}</p>
<p>userName 비었나? ${empty userName}</p>

<h2>6. 객체 및 컬렉션 접근</h2>
<p>
    <b>배열, 리스트 요소는 속성명[index]로 접근 가능</b> <br>
    첫 번째 과일: ${fruitList[0]} <br>
    두 번째 과일: ${fruitList[1]} <br>
</p>

<p>
    <b>Map의 저장된 값은 속성명.key 로 접근 가능 </b> <br>
    사용자 이메일: ${userInfo.email} <br>
    사용자 전화번호: ${userInfo["phone"]} <br>
</p>

<h2>7. 조건 연산자</h2>
<p>점수 결과: ${score >= 60 ? '합격' : '불합격'}</p>
</body>
</html>
