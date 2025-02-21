package org.example.booksearchweb.controller;

import org.example.booksearchweb.model.BoardVO;
import org.example.booksearchweb.model.MemberVO;
import org.example.booksearchweb.service.BoardService;
import org.example.booksearchweb.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

@WebServlet("/boardView")
public class BoardViewServlet extends HttpServlet {
    MemberService memberService = new MemberService();
    BoardService boardService = new BoardService();

    @Override
    public void init() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("doGet");
        String query = req.getParameter("query");
        List<BoardVO> boardList = null;
        if (query != null) {
            boardList = boardService.getBoardList(query);
        }else {
            boardList = boardService.getBoardList();

        }
        req.setAttribute("boardList", boardList);
        req.getRequestDispatcher("/boardView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        System.out.println("doPost");
    }
}
