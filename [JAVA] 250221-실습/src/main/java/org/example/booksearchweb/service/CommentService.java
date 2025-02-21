package org.example.booksearchweb.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.example.booksearchweb.dao.CommentDAO;
import org.example.booksearchweb.model.CommentVO;
import org.example.booksearchweb.util.MyBatisSessionFactory;

import java.util.List;

public class CommentService {
    private CommentDAO commentDAO;

    public CommentService() {
        SqlSessionFactory factory = MyBatisSessionFactory.getSqlSessionFactory();
        this.commentDAO = new CommentDAO(factory);
    }

    public List<CommentVO> getCommentList(int boardId) {
        return commentDAO.selectAllComment(boardId);
    }
    public CommentVO getCommentById(int id) {return commentDAO.selectCommentById(id);}

    // 등록한 댓글의 게시글 id를 반환해서 댓글 달고 해당 게시글 페이지에 있게 하려고 햇음
    // 댓글 아이디 반환으로 바꿈
    public int insertComment(CommentVO commentVO) {return commentDAO.insertComment(commentVO);}
    public boolean deleteComment(int id) {return commentDAO.deleteComment(id) > 0;}

    public CommentVO writeComment(int boardId, String userId, String content) {
        // CommentVO 객체 생성 및 속성 설정
        CommentVO comment = new CommentVO();
        comment.setBoardId(boardId);
        comment.setUserId(userId);
        comment.setContent(content);
        // regDate는 DB에서 자동 처리할 수도 있고, 여기서 세팅할 수도 있습니다.

        int result = commentDAO.insertComment(comment);
        if(result > 0) {
            // 성공적으로 삽입되었다면, 방금 추가된 댓글 객체 반환
            // 필요한 경우, 새롭게 생성된 comment id 등을 조회할 수 있음
            return comment;
        } else {
            // 실패 시 null 또는 예외 처리를 할 수 있습니다.
            return null;
        }
    }}
