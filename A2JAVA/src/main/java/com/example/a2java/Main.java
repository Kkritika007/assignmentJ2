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
        // Load the FXML file for the UI layout
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("convert-view.fxml"));

        // Create a new scene using the loaded FXML file
        Scene scene = new Scene(fxmlLoader.load());

        // Set stage properties
        stage.setTitle("Convert Currency"); // Set the title of the window
        stage.setResizable(false); // Disable window resizing

        // Set the scene for the stage
        stage.setScene(scene);

        // Add application icon to the stage
        stage.getIcons().add(new Image("file:src/main/resources/com/example/a2java/cclogo.jpg"));

        // Show the stage
        stage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch();
    }
}
