package example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class BookSearchView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // FXML로 화면 구성
        // Stage - scene - Parent(Layout manager)
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/booksearchview.fxml"));



        try {
            root = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book Search");
        stage.show();
    }

}
