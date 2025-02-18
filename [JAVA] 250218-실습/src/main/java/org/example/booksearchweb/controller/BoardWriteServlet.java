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
        String writer = req.getParameter("writer");

        BoardVO board = new BoardVO(title, content, writer);
        int id = boardService.insertBoard(board);
        System.out.println(id);

        List<BoardVO> boardList = boardService.getBoardList();

        req.setAttribute("boardList", boardList);
        req.getRequestDispatcher("/welcome.jsp").forward(req, resp); // Request Dispatcher 객체로 + .forward()
    }
}
