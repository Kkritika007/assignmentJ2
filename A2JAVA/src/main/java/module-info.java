module com.example.a2java {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;


    opens com.example.a2java to javafx.fxml, com.google.gson;
    exports com.example.a2java;
}