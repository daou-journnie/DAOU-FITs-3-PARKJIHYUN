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

@WebServlet("/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
    BoardService boardService = new BoardService();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int result = boardService.deleteBoard(id);

        List<BoardVO> boardList = boardService.getBoardList();
        req.setAttribute("boardList", boardList);
        resp.sendRedirect("./welcome.jsp");
    }

}
