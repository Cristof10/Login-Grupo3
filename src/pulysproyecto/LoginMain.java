package pulysproyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage login)throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(root);

        login.setMaximized(false);
        login.setResizable(false);
        login.setScene(scene);
        login.show();
    }
}
