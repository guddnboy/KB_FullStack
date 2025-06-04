package org.scoula.ex05;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //속성 설정
        req.setAttribute("username", "홍길동");
        req.setAttribute("useraddress", "서울");

        //forward
        RequestDispatcher dis = req.getRequestDispatcher("/res.jsp");
        dis.forward(req, res);

    }
}