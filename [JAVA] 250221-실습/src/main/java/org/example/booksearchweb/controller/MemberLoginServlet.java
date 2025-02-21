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

@WebServlet("/login")
public class MemberLoginServlet extends HttpServlet {
    MemberService memberService = new MemberService();
    BoardService boardService = new BoardService();

    @Override
    public void init() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("doGet");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        System.out.println("doPost");
        // 쿼리 파라미터에서 bookId 읽기
        String id = req.getParameter("id");
        String pw = req.getParameter("password");
        System.out.println(id + " " + pw);

        MemberVO member = memberService.login(id, pw);
        System.out.println("로그인된 member id: " + member.getId());

        if (member != null) {
            // WAS에게 Session 저장 공간 달라고 요청
            HttpSession session = req.getSession();
            // 기존에 할당된 session 객체가 이미 존재하면 가져다주고 없으면 null return
//            req.getSession(false);

            List<BoardVO> boardList = boardService.getBoardList();

            // setAttribute로 값 저장 꺼내올 때는 getAttribute
            session.setAttribute("member", member);
            req.setAttribute("boardList", boardList);
            req.getRequestDispatcher("/welcome.jsp").forward(req, resp); // Request Dispatcher 객체로 + .forward()


        } else {
            req.setAttribute("error", "로그인 실패! 아이디 또는 비밀번호를 확인하세요.");

            resp.sendRedirect("login.jsp");
        }
    }
}
