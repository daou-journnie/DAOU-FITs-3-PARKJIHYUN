package org.example.booksearchweb.controller;

import org.example.booksearchweb.model.BoardVO;
import org.example.booksearchweb.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/boardWrite")
public class BoardWriteServlet extends HttpServlet {
    BoardService boardService = new BoardService();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");  // 추가

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String userId = req.getParameter("userId");

        BoardVO board = new BoardVO();
        board.setTitle(title);
        board.setContent(content);
        board.setUserId(userId);
        int id = boardService.insertBoard(board);
        resp.sendRedirect("boardDetail?id="+id);
    }
}
