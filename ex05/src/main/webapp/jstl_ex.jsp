<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"   %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>JSTL 예제</title>
</head>
<body>
<%-- 예제 코드 작성 --%>
<c:set var="myString" value="  Hello JSTL Functions!  " />
<c:set var="email" value="test@example.com" />

<p>문자열 길이: ${fn:length(myString)}</p>
<p>앞뒤 공백 제거: '${fn:trim(myString)}'</p>
<p>모두 대문자: ${fn:toUpperCase(myString)}</p>
<p>'Functions' 포함 여부: ${fn:contains(myString, 'Functions')}</p>
<p>'test@example.com'이 '.com'으로 끝나는가? ${fn:endsWith(email, '.com')}</p>
<p>아이템 목록 연결: ${fn:join(items, ' | ')}</p>
</body>
</html>