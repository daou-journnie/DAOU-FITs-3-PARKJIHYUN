package org.example.booksearchweb.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.booksearchweb.model.BoardVO;
import org.example.booksearchweb.model.BookVO;
import org.example.booksearchweb.model.LikeVO;

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
            List<BoardVO> result = sqlSession.selectList("example.MyBoard.selectAllBoardwithQuery");
            list.addAll(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<BoardVO> selectAllBoard(String query) {
        List<BoardVO> list = new ArrayList<>();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<BoardVO> result = sqlSession.selectList("example.MyBoard.selectAllBoardwithQuery", query);
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
            board.setId(0);
            int result = sqlSession.insert("example.MyBoard.insertBoard", board);
            sqlSession.commit();
            System.out.println("board id : "+board.getId());
            return board.getId();
        } finally {
            sqlSession.close();
        }
    }

    public int updateBoard(BoardVO board) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.update("example.MyBoard.updateBoard", board);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }

    public int deleteBoard(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.delete("example.MyBoard.deleteBoard", id);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    // 조회수
    public int incrementViewCount(int boardId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.update("example.MyBoard.incrementViewCount", boardId);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }


    // 좋아요


    public int insertLike(int boardId, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.insert("example.MyBoard.insertLike", new LikeVO(boardId, userId));
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }

    public int deleteLike(int boardId, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("boardId", boardId);
            params.put("userId", userId);
            int result = sqlSession.delete("example.MyBoard.deleteLike", params);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }

    public boolean isLikedByUser(int boardId, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("boardId", boardId);
            params.put("userId", userId);
            // 좋아요가 있으면 1 이상의 값이 반환될 것입니다.
            Integer count = sqlSession.selectOne("example.MyBoard.selectLikeCountByUser", params);
            return count != null && count > 0;
        } finally {
            sqlSession.close();
        }
    }


}
