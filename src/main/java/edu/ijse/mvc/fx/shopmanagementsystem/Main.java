package edu.ijse.mvc.fx.shopmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/edu/ijse/mvc/fx/shopmanagementsystem/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Shop Management System");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
