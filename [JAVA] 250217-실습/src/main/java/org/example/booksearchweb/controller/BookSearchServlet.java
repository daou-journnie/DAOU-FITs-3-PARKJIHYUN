package org.example.booksearchweb.controller;

import org.example.booksearchweb.dao.BookDAO;
import org.example.booksearchweb.model.BookVO;
import org.example.booksearchweb.service.BookService;

import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/search")
public class BookSearchServlet extends HttpServlet {
    private BookService bookService = new BookService();

    public BookSearchServlet() {
//        this.dao = new BookDAO();
    }

    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        // Hello
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "hi" + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("doPost");
        req.setCharacterEncoding("UTF-8");
        String searchKeyword = req.getParameter("keyword");
        String price =req.getParameter("priceOption");
        System.out.println(searchKeyword + " " + price);

        List<BookVO> searchResult = bookService.searchByTitle(searchKeyword, price);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Search Result</title></head>");
        out.println("<body>");
        out.println("");
        out.println("<h3>"+"검색 키워드 : "+searchKeyword+"</h3>");
        out.println("<h3>"+"검색 가격 : "+price+"원 미만 </h3>");
        out.println("<ul>");
        for(BookVO book : searchResult){
            out.println("<li><a href='bookDetail?bookIsbn=" + book.getBisbn() + "'>"
                    + book.getBtitle() + "</a>, "
                    + book.getBprice() + "</li>");
        }
        out.println("</ul>");

    }
}
