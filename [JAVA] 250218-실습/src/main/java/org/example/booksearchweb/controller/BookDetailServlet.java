package org.example.booksearchweb.controller;
import org.example.booksearchweb.model.BookVO;
import org.example.booksearchweb.service.BookService;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/bookDetail")
public class BookDetailServlet extends HttpServlet {
    BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 쿼리 파라미터에서 bookId 읽기
        String bookIsbn = req.getParameter("bookIsbn");
        BookVO book = bookService.searchByISBN(bookIsbn);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Book Detail</title></head>");
        out.println("<body>");

        if(book != null){
            out.println("<h2>" + book.getBtitle() + "</h2>");
            out.println("<p>Price: " + book.getBprice() + "원</p>");
            // 상세정보가 있다면 추가 정보 출력
            out.println("<p>Author: " + book.getBauthor() + "</p>");
        } else {
            out.println("<h2>책 정보를 찾을 수 없습니다.</h2>");
        }

        out.println("<a href='javascript:history.back()'>뒤로가기</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
