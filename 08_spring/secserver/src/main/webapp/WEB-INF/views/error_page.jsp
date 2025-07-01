<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>시스템 오류</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 40px; }
    .error-container { max-width: 800px; }
    .error-message { color: #d32f2f; font-size: 18px; margin-bottom: 20px; }
    .stack-trace { background: #f5f5f5; padding: 15px; border-radius: 4px; }
    .stack-trace li { margin: 5px 0; font-family: monospace; font-size: 12px; }
  </style>
</head>
<body>
<div class="error-container">
  <h2>시스템 오류가 발생했습니다</h2>

  <div class="error-message">
    <strong>오류 메시지:</strong>
    <c:out value="${exception.getMessage()}"/>
  </div>

  <h3>상세 스택 트레이스:</h3>
  <div class="stack-trace">
    <ul>
      <c:forEach items="${exception.getStackTrace()}" var="stack">
        <li><c:out value="${stack}"/></li>
      </c:forEach>
    </ul>
  </div>

  <p><a href="/">홈으로 돌아가기</a></p>
</div>
</body>
</html>