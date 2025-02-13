package example.dao;

import example.model.BookVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;

// SqlSessionFactory를 받아올 거임
public class BookDAO {
    private SqlSessionFactory sqlSessionFactory;

    public BookDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }



    public ObservableList<BookVO> selectByISBNBookVO(String bisbn) {
        ObservableList<BookVO> list = FXCollections.observableArrayList();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // MyBatis가 반환한 List<BookVO>를 ObservableList로 변환
            List<BookVO> result = sqlSession.selectList("example.MyBook.selectByISBNBookVO", bisbn);
            list.addAll(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    // 2. 모든 책 정보를 HashMap에 담아서 전체 책을 list에
    public List<HashMap<String, Object>> selectAll() {
        List<HashMap<String, Object>> list = null;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            list = sqlSession.selectList("example.MyBook.selectAll");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }



    // 책 등록
    public int insertBookVO(BookVO book) {
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 기본은 auto-commit false
        try {
            int result = sqlSession.insert("example.MyBook.insertBookVO", book);
            // 변경사항을 커밋해야 DB에 반영됩니다.
            sqlSession.commit();
            return result;
        } catch(Exception e) {
            sqlSession.rollback(); // 에러 발생 시 롤백
            e.printStackTrace();
            return 0;
        } finally {
            sqlSession.close();
        }
    }


    // 책 검색
    public ObservableList<BookVO> searchByTitle(String keyword) {
        ObservableList<BookVO> list = FXCollections.observableArrayList();
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



    // 책 수정
    public int updateBookVO(BookVO bookVO) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.update("example.MyBook.updateBookVO", bookVO);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            return 0;
        } finally {
            sqlSession.close();
        }
    }

    public int deleteBookVO(String bisbn) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.delete("example.MyBook.deleteBookVO", bisbn);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            return 0;
        } finally {
            sqlSession.close();
        }
    }



    // 책 삭제


}
