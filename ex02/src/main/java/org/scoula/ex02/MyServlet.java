package org.scoula.ex02;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet(name = "myServlet", urlPatterns= {"/xxx", "/yyy" } )
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head><title>MyServlet 응답 페이지</title></head>");
        out.println("<body>");
        out.println("<h1>Hello World!</h1>");
        out.println("현재 시간 : " + LocalDate.now().toString());
        out.println("</body>");
        out.println("</html>");
    }

}

