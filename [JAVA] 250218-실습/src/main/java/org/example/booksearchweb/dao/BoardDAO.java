package org.example.booksearchweb.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.booksearchweb.model.BoardVO;
import org.example.booksearchweb.model.BookVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// SqlSessionFactory를 받아올 거임
public class BoardDAO {
    private SqlSessionFactory sqlSessionFactory;

    public BoardDAO(SqlSessionFactory factory) {
        this.sqlSessionFactory = factory;
    }

    public List<BoardVO> selectAllBoard() {
        List<BoardVO> list = new ArrayList<>();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<BoardVO> result = sqlSession.selectList("example.MyBoard.selectAllBoard");
            list.addAll(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public BoardVO selectBoardById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BoardVO board = null;
        board = sqlSession.selectOne("example.MyBoard.selectBoardById", id);
        return board;
    }

    public int insertBoard(BoardVO board) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.insert("example.MyBoard.insertBoard", board);
            sqlSession.commit();
            return 1;
        } finally {
            sqlSession.close();
        }
    }

//    public int updateBoard(BoardVO board) {}

    public int deleteBoard(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.delete("example.MyBoard.deleteBoard", id);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }






}
