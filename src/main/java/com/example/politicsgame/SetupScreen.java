package com.example.politicsgame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SetupScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox layout = new VBox(20); // 20 is the spacing between elements
        layout.setAlignment(Pos.CENTER);

        Label promptLabel = new Label("Input your Party Name here:");
        TextField partyNameInput = new TextField();
        partyNameInput.setPromptText("Enter your party's name");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            String partyName = partyNameInput.getText();

            if (!partyName.trim().isEmpty()) {
                Party userParty = new Party(partyName,true);
                Main.party = userParty;
                GameState.setPlayerParty(userParty);
                GameMap gameMap = GameMap.getInstance();
                System.out.println(GameMap.parties.size());
                try {
                    new GameScreen().start(new Stage());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                primaryStage.close();
            }
        });

        layout.getChildren().addAll(promptLabel, partyNameInput, submitButton);

        Scene scene = new Scene(layout, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
