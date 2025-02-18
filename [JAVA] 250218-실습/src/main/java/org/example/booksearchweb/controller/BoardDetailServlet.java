package org.example.booksearchweb.controller;

import org.example.booksearchweb.model.BoardVO;
import org.example.booksearchweb.model.BookVO;
import org.example.booksearchweb.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/boardDetail")
public class BoardDetailServlet extends HttpServlet {
    BoardService boardService = new BoardService();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        BoardVO board = boardService.getBoardById(id);

        // 데이터 세팅 후 바로 forward() 호출
        req.setAttribute("board", board);
        req.getRequestDispatcher("/boardDetail.jsp").forward(req, resp);
    }

}
