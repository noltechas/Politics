module com.example.politicsgame {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.politicsgame to javafx.fxml;
    exports com.example.politicsgame;
}