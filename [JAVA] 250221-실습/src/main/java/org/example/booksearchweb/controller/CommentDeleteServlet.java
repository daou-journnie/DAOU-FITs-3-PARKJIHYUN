package org.example.booksearchweb.controller;

import org.example.booksearchweb.model.CommentVO;
import org.example.booksearchweb.model.MemberVO;
import org.example.booksearchweb.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/commentDelete")
public class CommentDeleteServlet extends HttpServlet {
    CommentService commentService = new CommentService();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int commentId = Integer.parseInt(req.getParameter("commentId"));

        // 로그인한 사용자 정보 확인
        HttpSession session = req.getSession();
        MemberVO member = (MemberVO) session.getAttribute("member");
        String userId = member != null ? member.getId() : "";

        // 서비스 계층 호출 (댓글 삭제 요청)
        boolean success = commentService.deleteComment(commentId);

        // JSON 응답 생성
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print("{\"success\": " + success + "}");
        out.flush();
    }
}
