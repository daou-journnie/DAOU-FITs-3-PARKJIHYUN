package org.example.booksearchweb.controller;

import org.example.booksearchweb.model.BoardVO;
import org.example.booksearchweb.model.CommentVO;
import org.example.booksearchweb.model.MemberVO;
import org.example.booksearchweb.service.BoardService;
import org.example.booksearchweb.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/commentWrite")
public class CommentWriteServlet extends HttpServlet {
    CommentService commentService = new CommentService();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");  // 추가
        System.out.println("comment write post");

        Integer boardId = Integer.parseInt(req.getParameter("boardId"));
        String content = req.getParameter("content");
        String userId = req.getParameter("userId");
//        HttpSession session = req.getSession();
//        MemberVO member = (MemberVO) session.getAttribute("member");
//        String userId = member.getId();
        System.out.println(boardId + " " + content + " " + userId);

        CommentVO comment = new CommentVO(boardId, userId, content);
        int id = commentService.insertComment(comment);
        comment = commentService.getCommentById(id);

        // 예시로 작성된 댓글 객체를 JSON으로 응답
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print("{\"writer\": \"" + comment.getWriter()
                + "\", \"userId\": \"" + comment.getUserId()
                + "\", \"id\": \"" + comment.getId()
                + "\", \"content\": \"" + comment.getContent()
                + "\", \"regDate\": \"" + comment.getRegDate() + "\"}");
        out.flush();


    }


}
