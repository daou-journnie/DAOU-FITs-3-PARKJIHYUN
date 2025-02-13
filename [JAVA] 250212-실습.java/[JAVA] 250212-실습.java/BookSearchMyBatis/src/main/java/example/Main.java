package example;

import example.dao.BookDAO;
import example.util.MyBatisSessionFactory;
import example.model.BookVO;
import javafx.collections.ObservableList;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // DAO
        // 그런데 DAO 만들려면 SqlSessionFactory가 있어야함
        SqlSessionFactory factory = MyBatisSessionFactory.getSqlSessionFactory();
        BookDAO dao = new BookDAO(factory);
        HashMap map = dao.selectByISBNHashMap("89-7914-063-0");
        // 책 한 권 정보 저장됨
        // 키 값: 컬럼명, value: 값
        System.out.println(map);
        for (Object key : map.keySet()) {
            System.out.println(key + ":" + map.get(key));
        }

//        List<HashMap<String, Object>> list = dao.selectAll();
//        for (HashMap hmap : list) {
//            for (String key : hmap.keySet()) {
//                System.out.println(key + ":" + hmap.get(key));
//            }
//        }

        // bisbn을 이용해서 책 1권을 가져오는데 BookVO에 데이터를 담아서
//        BookVO vo = dao.selectByISBNBookVO("89-7914-063-0");
//        System.out.println(vo.toString());


        ObservableList<BookVO> list = dao.searchByTitle("여행");
//        for (BookVO book : list) {
//            System.out.println(book.toString());
//        }
        System.out.println(list);

        int insertBook = dao.insertBookVO(new BookVO("111", "aaa", 100, "AAA"));
        System.out.println(dao.selectByISBNBookVO("111"));



    }
}
