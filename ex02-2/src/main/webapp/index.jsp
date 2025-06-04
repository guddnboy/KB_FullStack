<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<a href="hello-servlet">Hello Servlet</a>
<hr>

<h3>JSP 기본 요소</h3>
<ol>
  <li><a href="basic/main.jsp">JSP 지시어 - include 지시어</a></li>
  <li><a href="basic/currentTime.jsp">스크립틀릿 태그, 표현식 태그</a></li>
  <li><a href="basic/response_redirect.jsp">response 내장 객체</a></li>
  <li><a href="basic/out.jsp">out 내장 객체</a></li>
  <li><a href="basic/divide.jsp">exception 객체</a></li>

  <li><a href="layout/main.jsp">jsp:include 1</a></li>
  <li><a href="layout/main2.jsp">jsp:include 2</a></li>


</ol>

<h3>Scope 관련 JSP 내장 객체</h3>
<ol>
  <li><a href="scope/page/pageContext.jsp"><b>page scope</b></a></li>
  <li><a href="scope/request/request.jsp"><b>request scope</b></a></li>
  <li>
    <b>session scope</b>
    <%@ include file="scope/session/loginInfo.jsp" %>
    <ul>
      <li><a href="scope/session/login.jsp">로그인</a></li>
      <li><a href="scope/session/logout.jsp">로그아웃</a></li>
    </ul>
  </li>

  <li>
    <b>application scope</b>
    <ul>
      <li><a href="scope/application/count.jsp">count 증가</a></li>
      <li><a href="scope/application/count_view.jsp">count 확인</a></li>
    </ul>
  </li>
</ol>

</body>
</html>