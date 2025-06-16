<%-- views/board/create.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<%@ include file="../layouts/header.jsp" %>

<h1 class="page-header my-4">
    <i class="far fa-edit"></i> 새 글쓰기
</h1>

<div>
    <!-- POST 방식으로 현재 URL에 전송 -->
    <form method="post">
        <div class="mb-3">
            <label class="form-label">제목</label>
            <input name="title" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">작성자</label>
            <input name="writer" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">내용</label>
            <textarea class="form-control" name="content" rows="10" required></textarea>
        </div>

        <div class="mt-4">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-check"></i> 확인
            </button>
            <button type="reset" class="btn btn-secondary">
                <i class="fas fa-undo"></i> 취소
            </button>
            <a href="list" class="btn btn-outline-primary">
                <i class="fas fa-list"></i> 목록
            </a>
        </div>
    </form>
</div>

<%@ include file="../layouts/footer.jsp" %>