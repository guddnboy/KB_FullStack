package org.scoula.ex05;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/el_example")
public class ELExample extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // request scope 객체에 값 세팅
        request.setAttribute("userName", "홍길동");
        request.setAttribute("score", 95);
        request.setAttribute("fruitList", new String[]{"사과", "바나나", "오렌지"});

        // EL에서 null, 빈칸(""), 비어있는 리스트 처리 방법 확인
        request.setAttribute("emptyVar", null);
        request.setAttribute("emptyString", "");
        request.setAttribute("emptyList", new java.util.ArrayList());

        // Map 객체를 생성하여 request scope에 세팅
        // -> EL을 이용한 Map 데이터 접근 방법 확인
        java.util.Map<String, String> userInfo = new java.util.HashMap<>();
        userInfo.put("email", "kb@example.com");
        userInfo.put("phone", "010-1234-5678");
        request.setAttribute("userInfo", userInfo);

        request.getRequestDispatcher("el_example.jsp").forward(request,response);


    }
}
