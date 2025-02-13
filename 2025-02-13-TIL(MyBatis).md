### Pure JDBC

SQL과 Java가 하나의 file에 공존 - DAO

→ 별도의 library, Framework 사용

- MyBatis
- ORM

# MyBatis

SQL 구문과 Java 코드 분리 가능

SQL 실행 결과를 Map에 매핑 시킴

DataSource 기능 - Connection Pool

![image.png](attachment:8fa81975-cc28-4c96-ba7e-055d44f5b3ac:image.png)

- 지금까지는 필요할 때마다 연결 요청
    - DB에 지속적인 연결 요청 - 다쓰면 닫기 좋지 않음

별도 클래스 만듦 - connection Pool

다 썼으면 닫는 게 아니라 pool에 빈닙

미리 만들어 놓은 거 가져다가 쓰기

Transaction이 깔끔한지 등의 처리가 필요

Tranasaction 기능

커밋/롤백 

## 설정하기

New Project → Maven

### 라이브러리

Project Structure → Libraries → lib(javafx), mybatis, ojdbc11

### Configuration XML

`resources/SqlMapConfig.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC
        "-//mybatis.org/DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd" >

<configuration>
    <!--JDBC Driver Class / Database Setting-->
<!--  연결 설정 내용은 XML 안에 직접 작성하지 않고 별도 외부 파일에 작성해서 해당 파일을 property로 가져와서 사용  -->
    <properties resource="./driver.properties"/>
    
    <!--  MyBatis 실행설정  -->
    <!--  상당히 많은 설정이 있지만 대부분 default 설정 이용  -->
    <settings>
        <setting name="jdbcTypeForNull" value="NULL"/>
    </settings>

    <!--  TypeAlias  -->
    <typeAliases>
        <typeAlias alias="Book" type="example.vo.BookVO" />
    </typeAliases>
    
    <!--  DB 연결 정보  -->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${db.driver}"/>
                <property name="url" value="${db.url}"/>
                <property name="username" value="${db.username}"/>
                <property name="password" value="${db.password}"/>
            </dataSource>
        </environment>
    </environments>

    <!--  SQL 구문 작성하는 mapper 파일 경로 지정  -->
    <mappers>
        <mapper resource="./sqlmap/Book.xml"/>
    </mappers>

</configuration>

```

- configuration
    - properties
        
        연결 설정 내용은 XML 안에 직접 작성하지 않고 별도 외부 파일에 작성해서 해당 파일을 property로 가져와서 사용
        
        `<properties resource="./driver.properties"/>` 정보는 이 안에 있다 표시
        
        [`driver.properties`](https://www.notion.so/MyBatis-1995832d922880bcb050e97f4e8a3246?pvs=21) 파일 만들기
        
    - settings
        
        MyBatis 런타임 설정하기
        
        상당히 많은 설정이 있지만 대부분 default 설정 이용
        
        - `<setting name="jdbcTypeForNull" value="NULL"/>`
            
            select에서 가져온 값이 없으면 NULL이라 하겠다
            
    - typeAliases
        
        주로 VO alias
        
        `<typeAlias alias="Book" type="example.vo.BookVO" />`
        
    - environments
        - environment id=”development”
            - transactionManager
                
                `<transactionManager type="JDBC"/>`
                
                transactionManager를 JDBC로 설정하면 수동으로 transaction 설정
                
                commit(), rollback()을 코드에서 제어 ⇒ jdbc
                
                type이 managed로 설정하면 자동으로 transaction 제어 가능
                
                `<dataSource type="POOLED"/>`
                
                커넥션풀 사용할게요
                
            - dataSource
                
                connection pool 사용 여부
                
                `<dataSource type="POOLED">`
                
                - property
                
                ```xml
                <property name="driver" value="${db.driver}"/>
                <property name="url" value="${db.url}"/>
                <property name="username" value="${db.username}"/>
                <property name="password" value="${db.password}"/>
                
                ```
                
    - mappers
        
        SQL 구문 작성하는 mapper 파일 경로 지정
        
        - mapper
            
            `<mapper resource="./sqlmap/Book.xml"/>`
            
            resources/sqlmap/Book.xml
            

${} 값을 그대로 들고와라

#{} ‘ ‘ 붙여서 들고와라

### driver.properties

```xml
db.driver = oracle.jdbc.driver.OracleDriver
db.url = jdbc:oracle:thin:@localhost:1521:xe
db.username = C##JDBC_PRACTICE
db.password = 1234
```

이때 db는 그냥 내가 설정한 별명

### Book.xml

sql 들어가는 파일

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC
        "-//mybatis.org/DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="example.MyBook">
    <!--  SELECT문 결과를 HASHMAP에 담겠다
            isbn type: String
            -->
    <select id="selectByISBNHashMap" parameterType="String" resultType="HashMap">
    <!--    CDATA는 SQL Query 안 특수문자(<, >, ...)가 tag로 인식되지 않도록 처리    -->
        <![CDATA[
            SELECT *
            FROM BOOK
            WHERE BISBN = #{bisbn}
        ]]>
    </select>

    <select id="selectByISBNBookVO" parameterType="String" resultType="Book">
        <![CDATA[
            SELECT *
            FROM BOOK
            WHERE BISBN = #{bisbn}
        ]]>
    </select>

    <select id="selectAll" resultType="HashMap">
        <![CDATA[
            SELECT *
            FROM BOOK
        ]]>
    </select>

    <insert parameterType="Book" id="insertBookVO">
        <![CDATA[
            INSERT INTO BOOK (BISBN, BTITLE, BPRICE, BAUTHOR)
            VALUES (#{bisbn}, #{btitle}, #{bprice}, #{bauthor})
        ]]>
    </insert>

    <select id="searchByTitle" parameterType="String" resultType="Book">
        <![CDATA[
            SELECT BISBN, BTITLE, BPRICE, BAUTHOR
            FROM BOOK
            WHERE BTITLE LIKE '%'||#{keyword}||'%'
        ]]>
    </select>

    <update parameterType="Book" id="updateBookVO">
        <!--    CDATA는 SQL Query 안 특수문자(<, >, ...)가 tag로 인식되지 않도록 처리    -->
        <![CDATA[
        UPDATE BOOK
        SET BTITLE = #{btitle},
            BPRICE = #{bprice},
            BAUTHOR = #{bauthor}
        WHERE BISBN = #{bisbn}
    ]]>
    </update>

    <delete parameterType="Book" id="deleteBookVO">
        <![CDATA[
        DELETE FROM BOOK WHERE BISBN = #{bisbn}
    ]]>
    </delete>

</mapper>
```

### SqlSessionFactory

```java
package example.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.Reader;

// SqlSession 객체를 뽑아내는 일 - SqlSession이 있어야 SQL 실행 가능
public class MyBatisSessionFactory {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "./SqlMapConfig.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            if(sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}

```

### VO

```java
package example.model;

public class BookVO {
    private String bisbn;
    private String btitle;
    private int bprice;
    private String bauthor;

    public BookVO() {  }

    public BookVO(String bisbn, String btitle, int bprice, String bauthor) {
        this.bisbn = bisbn;
        this.btitle = btitle;
        this.bprice = bprice;
        this.bauthor = bauthor;
    }

    public String getBisbn() {
        return bisbn;
    }

    public void setBisbn(String bisbn) {
        this.bisbn = bisbn;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public int getBprice() {
        return bprice;
    }

    public void setBprice(int bprice) {
        this.bprice = bprice;
    }

    public String getBauthor() {
        return bauthor;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    @Override
    public String toString() {
        return "BookVO{" +
                "bisbn='" + bisbn + '\'' +
                ", btitle='" + btitle + '\'' +
                ", bprice=" + bprice +
                ", bauthor='" + bauthor + '\'' +
                '}';
    }
}

```

### DAO

```java
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

```

### Main
