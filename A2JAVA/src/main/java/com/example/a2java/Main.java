package com.example.a2java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("convert-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Convert Currency");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/com/example/a2java/cclogo.jpg"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}