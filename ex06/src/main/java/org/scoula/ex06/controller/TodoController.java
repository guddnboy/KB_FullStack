package org.scoula.ex06.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class TodoController {

    // Todo 목록 조회 처리 (GET /todo/list)
    public String getList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<String> list = Arrays.asList("Todo 1", "Todo 2", "Todo 3");
        req.setAttribute("todoList", list);
        System.out.println("GET /todo/list");
        return "todo/list";
    }

    // Todo 상세보기 처리 (GET /todo/view)
    public String getView(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("GET /todo/view");
        return "todo/view";
    }

    // Todo 생성 폼 표시 (GET /todo/create)
    public String getCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("GET /todo/create");
        return "todo/create";
    }

    // Todo 생성 처리 (POST /todo/create)
    public String postCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("POST /todo/create");
        return "redirect:/todo/list";
    }

    // Todo 수정 폼 표시 (GET /todo/update)
    public String getUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("GET /todo/update");
        return "todo/update";
    }

    // Todo 수정 처리 (POST /todo/update)
    public String postUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("POST /todo/update");
        return "redirect:/todo/list";
    }

    // Todo 삭제 처리 (POST /todo/delete)
    public String postDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("POST /todo/delete");
        return "redirect:/todo/list";
    }
}