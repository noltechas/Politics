package com.example.politicsgame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SetupScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a layout
        VBox layout = new VBox(20); // 20 is the spacing between elements
        layout.setAlignment(Pos.CENTER); // Align elements to the center

        // Create a TextField for user input
        TextField partyNameInput = new TextField();
        partyNameInput.setPromptText("Enter your party's name");

        // Create a Button to submit the input
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            String partyName = partyNameInput.getText();

            if (!partyName.trim().isEmpty()) {
                // Store the party name somewhere accessible to the rest of the application
                // This could be in a static field, a file, a database, etc.
                // For simplicity, we'll use a static field in the main class
                Main.partyName = partyName;

                // Launch the main game screen
                try {
                    new GameScreen().start(new Stage());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                // Close the setup screen
                primaryStage.close();
            }
        });

        // Add the TextField and Button to the layout
        layout.getChildren().addAll(partyNameInput, submitButton);

        // Create a scene and add the layout to it
        Scene scene = new Scene(layout, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
