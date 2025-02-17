package org.example.booksearchweb.dao;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.booksearchweb.model.BookVO;
import org.example.booksearchweb.util.MyBatisSessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// SqlSessionFactory를 받아올 거임
public class BookDAO {
    private SqlSessionFactory sqlSessionFactory;

    public BookDAO(SqlSessionFactory factory) {
        this.sqlSessionFactory = factory;
    }

    public BookVO selectByISBN(String bisbn) {
        BookVO book = null;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // MyBatis가 반환한 List<BookVO>를 ObservableList로 변환
            book = sqlSession.selectOne("example.MyBook.selectByISBNBookVO", bisbn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return book;
    }


    // 책 검색
    public List<BookVO> selectByTitle(String keyword) {
        List<BookVO> list = new ArrayList<BookVO>();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // MyBatis가 반환한 List<BookVO>를 ObservableList로 변환
            List<BookVO> result = sqlSession.selectList("example.MyBook.searchByTitle", keyword);
            list.addAll(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<BookVO> selectByTitle(String keyword, String price) {
        List<BookVO> list = new ArrayList<>();
        System.out.println(keyword + " " + price);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("keyword", keyword);
            params.put("price", price);
            System.out.println(params);
            List<BookVO> result = sqlSession.selectList("example.MyBook.searchByTitlefilterByPrice", params);
            list.addAll(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }





}
