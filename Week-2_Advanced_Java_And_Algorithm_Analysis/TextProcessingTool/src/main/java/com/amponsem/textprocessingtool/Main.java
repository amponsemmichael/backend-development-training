package com.amponsem.textprocessingtool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/amponsem/textprocessingtool/views/main.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Text Processing Tool");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}