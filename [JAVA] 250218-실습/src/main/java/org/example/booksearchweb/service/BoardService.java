package org.example.booksearchweb.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.example.booksearchweb.dao.BoardDAO;
import org.example.booksearchweb.dao.BookDAO;
import org.example.booksearchweb.model.BoardVO;
import org.example.booksearchweb.model.BookVO;
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
    public BoardVO getBoardById(int id) {return boardDAO.selectBoardById(id);}
    public int insertBoard(BoardVO boardVO) {return boardDAO.insertBoard(boardVO);}
    public int deleteBoard(int id) {return boardDAO.deleteBoard(id);}

}
