<%-- views/board/create.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<%@ include file="../layouts/header.jsp" %>

<h1 class="page-header my-4">
    <i class="far fa-edit"></i> 새 글쓰기
</h1>
<!-- views/board/create.jsp -->
<div class="container">
    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label>제목</label>
            <input name="title" class="form-control">
        </div>

        <div class="form-group">
            <label>작성자</label>
            <input name="writer" class="form-control">
        </div>

        <!-- 파일 업로드 필드 -->
        <div class="form-group">
            <label>첨부파일</label>
            <input type="file"
                   class="form-control-file border"
                   multiple
                   name="files"/>
        </div>

        <div class="form-group">
            <label>내용</label>
            <textarea class="form-control" name="content" rows="10"></textarea>
        </div>

        <div class="form-group text-center">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-check"></i> 등록
            </button>
            <button type="reset" class="btn btn-warning">
                <i class="fas fa-redo"></i> 취소
            </button>
        </div>
    </form>
</div>
<%@ include file="../layouts/footer.jsp" %>