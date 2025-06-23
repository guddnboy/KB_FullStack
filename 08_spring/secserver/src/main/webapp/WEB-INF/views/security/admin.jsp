<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Security Test Page</title>
</head>
<body>
<h1>/security/admin page</h1>
<!-- 각 페이지에 맞게 제목 수정 -->
<!-- CSRF 토큰이 포함된 로그아웃 폼 -->
<form action="/security/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="submit" value="로그아웃"/>
</form>
</body>
</html>