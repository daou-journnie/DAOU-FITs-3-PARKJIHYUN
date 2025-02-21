package org.example.booksearchweb.controller;

import org.example.booksearchweb.model.BoardVO;
import org.example.booksearchweb.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/boardEdit")
public class BoardEditServlet extends HttpServlet {
    BoardService boardService = new BoardService();

    // GET: 수정할 게시글의 기존 데이터를 가져와 JSP에 전달
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if(idStr != null) {
            int id = Integer.parseInt(idStr);
            BoardVO board = boardService.getBoardById(id);
            req.setAttribute("board", board);
        }
        req.getRequestDispatcher("/boardEdit.jsp").forward(req, resp);
    }

    // POST: 수정된 데이터를 받아 DB에 업데이트 후 상세보기 페이지로 이동
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        BoardVO board = new BoardVO();
        board.setId(id);
        board.setTitle(title);
        board.setContent(content);

        boardService.updateBoard(board);
        resp.sendRedirect("boardDetail?id=" + id);
    }
}
