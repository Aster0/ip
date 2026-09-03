package carl.ui;

import carl.Carl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            Carl carl = new Carl();
            carl.start();
            fxmlLoader.<MainWindow>getController().setCarl(carl);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
