package org.example.booksearchweb.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 현재 세션이 존재하면 가져와서 무효화합니다.
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // 로그아웃 후 로그인 페이지로 리다이렉트합니다.
        resp.sendRedirect("login.jsp");
    }
}
