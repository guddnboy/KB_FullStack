<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL 내장 객체</title>
</head>
<body>

<h1>Scope 데이터 보기</h1>
pageScope의 속성값은 : ${pageScope.scopeName}<br>
requestScope의 속성값은 : ${requestScope.scopeName}<br>
sessionScope의 속성값은 : ${sessionScope.scopeName}<br>
applicationScope의 속성값은 : ${applicationScope.scopeName}<br>
scopeName 자동 찾기 : ${requestScope.scopeName}<br>
member: ${member.name}(${member.userid})<br>

<%--<h1>Scope 객체에 설정된 속성 값(데이터) 얻어오기</h1>--%>

<%--<h3>같은 속성 명의 값을 Scope별로 얻어오기</h3>--%>
<%--pageScope의 속성값은 : ${pageScope.scopeName}<br>--%>
<%--requestScope의 속성값은 : ${requestScope.scopeName}<br>--%>
<%--sessionScope의 속성값은 : ${sessionScope.scopeName}<br>--%>
<%--applicationScope의 속성값은 : ${applicationScope.scopeName}<br>--%>

<%--<hr>--%>
<%--<h3>Scope 우선 순위 자동 탐색</h3>--%>
<%--<p>--%>
<%--    <b>좁은 범위의 객체가 우선순위가 높음!</b> <br>--%>
<%--    scopeName 자동 탐색: ${scopeName} <br><br>--%>

<%--    page scope가 제일 좁은 범위이지만 pageScope.scopeName이 존재하지 않기 때문에<br>--%>
<%--    그 다음으로 좁은 범위를 가지는 request scope의 scopeName이 출력됨--%>
<%--</p>--%>


<%--<hr>--%>
<%--<h3>EL을 이용한 Domain(VO) 객체 값 얻어오기</h3>--%>
<%--<ol>--%>
<%--    <li>Domain 객체를 세팅한 속성 명 작성 -> 객체.toString() 호출</li>--%>
<%--    <li>속성명.필드명 -> 해당 필드의 Getter 메서드 호출<br>--%>
<%--        <b style="color:red;">Getter 메서드가 없을 경우 출력 X</b> <br>--%>
<%--        (PropertyNotFoundException: [name] 특성이 [org.scoula.ex05.domain.Member] 유형에 없습니다)--%>
<%--    </li>--%>
<%--</ol>--%>

<%--member: ${member}<br>--%>
<%--member.name: ${member.name}<br>--%>
<%--member.userid: ${member.userid}<br>--%>
</body>
</html>

</html>
