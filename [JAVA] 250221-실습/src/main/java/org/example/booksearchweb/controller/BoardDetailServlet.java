package org.example.booksearchweb.controller;

import org.example.booksearchweb.model.BoardVO;
import org.example.booksearchweb.model.BookVO;
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
import java.util.List;

@WebServlet("/boardDetail")
public class BoardDetailServlet extends HttpServlet {
    BoardService boardService = new BoardService();
    CommentService commentService = new CommentService();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        // 조회수 증가
        boardService.increaseViewCount(id);
        HttpSession session = req.getSession();
        MemberVO member = (MemberVO) session.getAttribute("member");
        String userId = member.getId();
        boolean isLikedByCurrentUser = boardService.isLikedByUser(id, userId);
        BoardVO board = boardService.getBoardById(id);
        List<CommentVO> commentList = commentService.getCommentList(id);

        // 데이터 세팅 후 바로 forward() 호출
        req.setAttribute("board", board);
        req.setAttribute("commentList", commentList);
        req.setAttribute("isLikedByCurrentUser", isLikedByCurrentUser);
        req.getRequestDispatcher("/boardDetail.jsp").forward(req, resp);
    }

}
