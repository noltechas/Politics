module com.example.politicsgame {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;

    opens com.example.politicsgame to javafx.fxml, com.google.gson; // Open the package to com.google.gson
    opens com.example.politicsgame.Events to com.google.gson;
    exports com.example.politicsgame;
}
