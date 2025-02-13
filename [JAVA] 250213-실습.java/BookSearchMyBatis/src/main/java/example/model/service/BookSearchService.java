package example.model.service;

import example.dao.BookDAO;
import example.model.BookVO;
import javafx.collections.ObservableList;
import org.apache.ibatis.session.SqlSessionFactory;
import example.util.MyBatisSessionFactory;

public class BookSearchService {
    private BookDAO dao;

    public BookSearchService() {
        SqlSessionFactory factory = MyBatisSessionFactory.getSqlSessionFactory();
        this.dao = new BookDAO(factory);
    }

    public ObservableList<BookVO> searchBooksByTitle(String title) {
        return dao.searchByTitle(title);
    }

    public int updateBook(BookVO book) {
        return dao.updateBookVO(book);
    }

    public int insertBook(BookVO book) {
        return dao.insertBookVO(book);
    }

    public ObservableList<BookVO> selectByISBN(String bisbn) {
        return dao.selectByISBNBookVO(bisbn);
    }

    public int deleteBook(String bisbn) {
        return dao.deleteBookVO(bisbn);
    }
}
