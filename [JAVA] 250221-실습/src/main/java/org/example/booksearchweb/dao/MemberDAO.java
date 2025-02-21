package org.example.booksearchweb.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.booksearchweb.model.MemberVO;

import java.util.List;

public class MemberDAO {
    private SqlSessionFactory sqlSessionFactory;
    public MemberDAO(SqlSessionFactory factory) {
        this.sqlSessionFactory = factory;
    }

    public List<MemberVO> selectAllMember() {
        SqlSession session = sqlSessionFactory.openSession();
        List<MemberVO> list = session.selectList("memberMapper.selectAllMember");
        session.close();
        return list;

    }

    public MemberVO getMemberById(String id) {
        System.out.println(id);
        MemberVO memberVO = new MemberVO();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            memberVO = sqlSession.selectOne("example.MyMember.selectMemberById", id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        System.out.println("seleced from query: "+memberVO);
        return memberVO;
    }
}
