package org.example.booksearchweb.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.example.booksearchweb.dao.MemberDAO;
import org.example.booksearchweb.model.MemberVO;
import org.example.booksearchweb.util.MyBatisSessionFactory;


public class MemberService {
    private MemberDAO memberDAO;
    public MemberService() {
        SqlSessionFactory factory = MyBatisSessionFactory.getSqlSessionFactory();
        this.memberDAO = new MemberDAO(factory);
    }

    public MemberVO login(String id, String pw) {
        System.out.println("Service : " + id + " : " + pw);
        MemberVO memberVO = null;
        try {
            memberVO = memberDAO.getMemberById(id);
            System.out.println("member from dao : "+memberVO);
            if(memberVO==null) {return null;}
            if(memberVO.getPw().equals(pw)) {
                return memberVO;
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("일치하는 아이디 없음");
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



        // 로그인 실패 상황 로직 더 만들어야함????

        return null;
    }

    public MemberVO getMemberById(String id) {
        return memberDAO.getMemberById(id);
    }
}
