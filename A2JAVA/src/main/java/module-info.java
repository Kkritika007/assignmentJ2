module com.example.a2java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.a2java to javafx.fxml;
    exports com.example.a2java;
}