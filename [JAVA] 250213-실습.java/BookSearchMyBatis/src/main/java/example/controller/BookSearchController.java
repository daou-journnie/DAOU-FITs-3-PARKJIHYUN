package example.controller;

import example.dao.BookDAO;
import example.model.service.BookSearchService;
import example.util.MyBatisSessionFactory;
import example.model.BookVO;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.util.converter.IntegerStringConverter;
import org.apache.ibatis.session.SqlSessionFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class BookSearchController implements Initializable {
    private BookSearchService service = new BookSearchService();

    @FXML TextField isbnTextField;
    @FXML TextField titleTextField;
    @FXML TextField priceTextField;
    @FXML TextField authorTextField;

    @FXML TextField searchTextField;
    @FXML Button searchBtn;
    @FXML Button insertBtn;
    @FXML Button deleteBtn;
    @FXML TableView<BookVO> tableView;
    @FXML TableColumn<BookVO, String> isbnCol;
    @FXML TableColumn<BookVO, String> titleCol;
    @FXML TableColumn<BookVO, String> authorCol;
    @FXML TableColumn<BookVO, Integer> priceCol;

    // 기본 생성자 반드시 있어야 함
    public BookSearchController() {
        System.out.println("Controller Default Constructor");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // view가 실행되면서 내부에서 controller 객체가 만들어짐 - 내가 안해도 initialize 메서드가 돌아간다는 뜻인듯
        System.out.println("Book Search Controller");

        SqlSessionFactory factory = MyBatisSessionFactory.getSqlSessionFactory();
        BookDAO dao = new BookDAO(factory);

        tableView.setEditable(true);
        // View controls에 대한 이벤트 등록
        // 이벤트 처리 등록하려면  View에 있는 control에 대한 reference 있어야함
        // vo 필드를 해당 컬럼에 설정
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
        // 제목 컬럼 편집 이벤트
        titleCol.setCellValueFactory(new PropertyValueFactory<>("btitle"));
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        titleCol.setOnEditCommit(event -> {
            BookVO book = event.getTableView().getItems().get(event.getTablePosition().getRow());
            String newTitle = event.getNewValue();
            book.setBtitle(newTitle);
            dao.updateBookVO(book); // 전체 행 업데이트
        });

// 가격 컬럼 편집 이벤트 (정수형 변환 고려)
        priceCol.setCellValueFactory(new PropertyValueFactory<>("bprice"));
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        priceCol.setOnEditCommit(event -> {
            BookVO book = event.getTableView().getItems().get(event.getTablePosition().getRow());
            try {
                int newPrice = event.getNewValue();
                book.setBprice(newPrice);
                dao.updateBookVO(book); // 전체 행 업데이트
            } catch (NumberFormatException e) {
                // 에러 처리: 올바른 정수 값 입력 안내
                e.printStackTrace();
            }
        });

// 저자 컬럼 편집 이벤트
        authorCol.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
        authorCol.setCellFactory(TextFieldTableCell.forTableColumn());
        authorCol.setOnEditCommit(event -> {
            BookVO book = event.getTableView().getItems().get(event.getTablePosition().getRow());
            String newAuthor = event.getNewValue();
            book.setBauthor(newAuthor);
            dao.updateBookVO(book); // 전체 행 업데이트
        });


        // 삽입 버튼 이벤트
        insertBtn.setOnAction(event -> {
            try {
                BookVO newBook = new BookVO(
                        isbnTextField.getText(),
                        titleTextField.getText(),
                        Integer.parseInt(priceTextField.getText()),
                        authorTextField.getText()
                );
                service.insertBook(newBook);
                // 삽입 후 ISBN으로 검색하여 TableView 갱신
                ObservableList<BookVO> list = service.selectByISBN(newBook.getBisbn());
                tableView.setItems(list);
            } catch (NumberFormatException e) {
                System.err.println("가격 필드에 올바른 정수를 입력하세요.");
            }
        });

        // 검색 버튼 이벤트
        searchBtn.setOnAction(event -> {
            ObservableList<BookVO> list = service.searchBooksByTitle(searchTextField.getText());
            tableView.setItems(list);
        });

        // 삭제 버튼 이벤트
        deleteBtn.setOnAction(e -> {
            BookVO selectedBook = tableView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                int result = service.deleteBook(selectedBook.getBisbn());
                if (result > 0) {
                    tableView.getItems().remove(selectedBook);
                    System.out.println("삭제 성공!");
                } else {
                    System.out.println("DB 삭제 실패!");
                }
            } else {
                System.out.println("선택된 행이 없습니다.");
            }
        });
    }
}
