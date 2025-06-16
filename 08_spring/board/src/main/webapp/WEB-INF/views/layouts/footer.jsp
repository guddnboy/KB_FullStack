<%-- views/layouts/footer.jsp --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
</div> <!-- end of .container -->

<footer class="text-center p-3 footer mt-5">
    <i class="fa-regular fa-copyright"></i>
    2025.6 created by java-backend
</footer>

<%-- 범위 객체에 message 속성이 존재하는 경우 alert로 출력--%>
<c:if test="${not empty message}">
    <script>
        alert("${message}");
    </script>
</c:if>
</body>
</html>