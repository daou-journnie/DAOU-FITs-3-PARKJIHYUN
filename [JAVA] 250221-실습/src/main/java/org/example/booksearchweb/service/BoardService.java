package org.example.booksearchweb.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.example.booksearchweb.dao.BoardDAO;
import org.example.booksearchweb.model.BoardVO;
import org.example.booksearchweb.util.MyBatisSessionFactory;

import java.util.List;

public class BoardService {
    // 여러가지 business logic울 처리하는 method 작성
    // DB는 DAO
    // Transaction 처리 때문에 DAO에는 SqlSession 객체를 injection해서 처리해야함
    private BoardDAO boardDAO;

    public BoardService() {
        SqlSessionFactory factory = MyBatisSessionFactory.getSqlSessionFactory();
        this.boardDAO = new BoardDAO(factory);
    }

    public List<BoardVO> getBoardList() {
        return boardDAO.selectAllBoard();
    }
    public List<BoardVO> getBoardList(String query) {return boardDAO.selectAllBoard(query); }
    public BoardVO getBoardById(int id) {return boardDAO.selectBoardById(id);}
    public int insertBoard(BoardVO boardVO) {return boardDAO.insertBoard(boardVO);}
    public int updateBoard(BoardVO boardVO) {return boardDAO.updateBoard(boardVO);}
    public int deleteBoard(int id) {return boardDAO.deleteBoard(id);}

    public void increaseViewCount(int boardId) {
        boardDAO.incrementViewCount(boardId);
    }
    // 좋아요 추가
    public boolean addLike(int boardId, String userId) {
        return boardDAO.insertLike(boardId, userId) > 0;
    }

    // 좋아요 취소
    public boolean removeLike(int boardId, String userId) {
        return boardDAO.deleteLike(boardId, userId) > 0;
    }

    public boolean isLikedByUser(int boardId, String userId) {
        return boardDAO.isLikedByUser(boardId, userId);
    }


}
