package org.example.booksearchweb.controller;

import org.example.booksearchweb.model.BoardVO;
import org.example.booksearchweb.model.MemberVO;
import org.example.booksearchweb.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/boardLike")
public class BoardLikeServlet extends HttpServlet {
    BoardService boardService = new BoardService(); // BoardService에 좋아요 관련 메서드를 추가

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int boardId = Integer.parseInt(req.getParameter("boardId"));
        String action = req.getParameter("action");

        HttpSession session = req.getSession();
        MemberVO member = (MemberVO) session.getAttribute("member");
        if(member == null) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        // 세션에서 가져온 userId 사용
        String userId = member.getId();

        boolean liked = false;
        int likeCount = 0;
        if("like".equals(action)) {
            liked = boardService.addLike(boardId, userId);
        } else if("unlike".equals(action)) {
            liked = boardService.removeLike(boardId, userId);
        }
        BoardVO board = boardService.getBoardById(boardId);
        likeCount = board.getLikeCount();

        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print("{\"liked\": " + liked + ", \"likeCount\": " + likeCount + "}");
        out.flush();
    }

}

