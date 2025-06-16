<%-- views/board/update.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<%@ include file="../layouts/header.jsp" %>

<h1 class="page-header my-4">
    <i class="far fa-edit"></i> 글 수정
</h1>

<div>
    <form role="form" method="post">
        <!-- 게시글 번호 숨김 필드 (수정 시 필수) -->
        <input type="hidden" name="no" value="${board.no}">

        <div class="mb-3">
            <label class="form-label">제목</label>
            <input name="title" class="form-control" value="${board.title}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">작성자</label>
            <input name="writer" class="form-control" value="${board.writer}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">내용</label>
            <textarea class="form-control" name="content" rows="10" required>${board.content}</textarea>
        </div>

        <div class="mt-4">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-check"></i> 확인
            </button>
            <button type="reset" class="btn btn-secondary">
                <i class="fas fa-undo"></i> 취소
            </button>
            <a href="get?no=${board.no}" class="btn btn-outline-primary">
                <i class="fas fa-file-alt"></i> 돌아가기
            </a>
        </div>
    </form>
</div>

<%@ include file="../layouts/footer.jsp" %>
