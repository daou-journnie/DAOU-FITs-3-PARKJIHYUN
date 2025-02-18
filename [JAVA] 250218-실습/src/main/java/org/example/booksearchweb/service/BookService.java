package org.example.booksearchweb.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.example.booksearchweb.dao.BookDAO;
import org.example.booksearchweb.model.BookVO;
import org.example.booksearchweb.util.MyBatisSessionFactory;

import java.util.List;

public class BookService {
    // 여러가지 business logic울 처리하는 method 작성
    // DB는 DAO
    // Transaction 처리 때문에 DAO에는 SqlSession 객체를 injection해서 처리해야함
    private BookDAO bookDAO;

    public BookService() {
        SqlSessionFactory factory = MyBatisSessionFactory.getSqlSessionFactory();
        this.bookDAO = new BookDAO(factory);
    }

    public List<BookVO> searchByTitle(String keyword, String price) {
        return bookDAO.selectByTitle(keyword, price);
    }

    public BookVO searchByISBN(String bisbn) {
        return bookDAO.selectByISBN(bisbn);
    }
}
