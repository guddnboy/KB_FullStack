<%-- views/board/get.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<%@ include file="../layouts/header.jsp" %>

<!-- 게시글 제목 -->
<h1 class="page-header my-4">
    <i class="far fa-file-alt"></i> ${board.title}
</h1>

<!-- 작성자 및 등록일 정보 -->
<div class="d-flex justify-content-between">
    <div>
        <i class="fas fa-user"></i> ${board.writer}
    </div>
    <div>
        <i class="fas fa-clock"></i>
        <fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}"/>
    </div>
</div>


<!-- 첨부파일 목록 -->
<div class="text-end">
    <c:forEach var="file" items="${board.attaches}">
        <div class="attach-file-item">
            <a href="/board/download/${file.no}" class="file-link">
                <i class="fa-solid fa-floppy-disk"></i>
                    ${file.filename} (${file.fileSize})
            </a>
        </div>
    </c:forEach>
</div>

<hr>

<!-- 게시글 내용 -->
<div class="content">
    ${board.content}
</div>

<!-- 액션 버튼들 -->
<div class="mt-4">
    <%--
        - 현재 주소 : /board/get
        - 요청 주소 : /board/list
    --%>

    <a href="list" class="btn btn-primary">
        <i class="fas fa-list"></i> 목록
    </a>
    <a href="update?no=${board.no}" class="btn btn-primary">
        <i class="far fa-edit"></i> 수정
    </a>
    <a href="#" class="btn btn-primary delete">
        <i class="fas fa-trash-alt"></i> 삭제
    </a>
</div>

<!-- 삭제용 숨겨진 폼 -->
<form action="delete" method="post" id="deleteForm">
    <input type="hidden" name="no" value="${board.no}"/>
</form>

<!-- 게시판 전용 JavaScript -->
<script src="/resources/js/board.js"></script>

<%@ include file="../layouts/footer.jsp" %>
