<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>메인 페이지</title>
</head>
<body>
<h1>환영합니다!</h1>

<!-- 로그인하지 않은 경우 -->
<sec:authorize access="isAnonymous()">
    <p>로그인이 필요합니다.</p>
    <a href="/security/login">로그인</a>
</sec:authorize>

<!-- 로그인한 경우 -->
<sec:authorize access="isAuthenticated()">
    <p>안녕하세요, <sec:authentication property="principal.username"/>님!</p>

    <!-- 사용자 정보 표시 -->
    <div>
        <p>이메일: <sec:authentication property="principal.member.email"/></p>
        <p>등록일: <sec:authentication property="principal.member.regDate"/></p>
    </div>

    <!-- 권한별 메뉴 표시 -->
    <nav>
        <sec:authorize access="hasRole('ADMIN')">
            <a href="/security/admin">관리자 페이지</a>
        </sec:authorize>

        <sec:authorize access="hasAnyRole('MEMBER','ADMIN')">
            <a href="/security/member">회원 페이지</a>
        </sec:authorize>
    </nav>

    <!-- 로그아웃 폼 -->
    <form action="/security/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" value="로그아웃"/>
    </form>
</sec:authorize>

<!-- 특정 권한자만 볼 수 있는 링크 -->
<sec:authorize access="hasRole('ADMIN')">
    <div style="border: 2px solid red; padding: 10px;">
        <h3>🔥 관리자 전용 메뉴</h3>
        <a href="/admin/users">사용자 관리</a> |
        <a href="/admin/system">시스템 설정</a>
    </div>
</sec:authorize>
</body>
</html>
