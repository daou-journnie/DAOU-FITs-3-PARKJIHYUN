package org.example.booksearchweb.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.booksearchweb.model.CommentVO;

import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private SqlSessionFactory sqlSessionFactory;

    public CommentDAO(SqlSessionFactory factory) {
        this.sqlSessionFactory = factory;
    }

    public List<CommentVO> selectAllComment(int boardId) {
        List<CommentVO> list = new ArrayList<>();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<CommentVO> result = sqlSession.selectList("example.MyComment.selectAllComment", boardId);
            list.addAll(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public CommentVO selectCommentById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CommentVO comment = null;
        comment = sqlSession.selectOne("example.MyComment.selectCommentById", id);
        return comment;
    }

    public int insertComment(CommentVO comment) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.insert("example.MyComment.insertComment", comment);
            sqlSession.commit();
            System.out.println(comment.getId());
            return comment.getId();
        } finally {
            sqlSession.close();
        }
    }

//    public int updateComment(CommentVO comment) {}

    public int deleteComment(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.delete("example.MyComment.deleteComment", id);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
