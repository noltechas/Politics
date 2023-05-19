package com.example.politicsgame;

import com.example.politicsgame.Events.Decision;
import com.example.politicsgame.Events.Event;
import com.example.politicsgame.Events.ResolveEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class EventWindow {
    private static final double WINDOW_WIDTH = 600;
    private static final double WINDOW_HEIGHT = 400;
    private static final double COLUMN_SPACING = 20;
    private static final double BUTTON_WIDTH = 100;
    private static final double BUTTON_HEIGHT = 40;

    private Event event;

    public EventWindow(Event event) {
        this.event = event;
    }

    public void displayWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(event.getName());
        window.setMinWidth(WINDOW_WIDTH);
        window.setMinHeight(WINDOW_HEIGHT);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

        // Load custom font
        Font ancientFont = Font.loadFont(getClass().getResourceAsStream("/Deutsch.ttf"), 20);

        // Event description
        Label descriptionLabel;
        if (event.getLocation().equals("whole_kingdom")) {
            descriptionLabel = new Label(event.getDescription());
        } else {
            descriptionLabel = new Label(event.getDescription() + event.getLocation());
        }
        descriptionLabel.setFont(Font.font(ancientFont.getFamily(), FontWeight.BOLD, 32));
        descriptionLabel.setTextFill(Color.BLACK);
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(WINDOW_WIDTH - 20);
        descriptionLabel.setTextAlignment(TextAlignment.CENTER);

        vbox.getChildren().add(descriptionLabel);

        // Event decisions
        HBox decisionsContainer = new HBox();
        decisionsContainer.setAlignment(Pos.CENTER);
        decisionsContainer.setSpacing(COLUMN_SPACING);
        vbox.getChildren().add(decisionsContainer);

        // Create a column for each decision
        for (Decision decision : event.getDecisions()) {
            VBox decisionColumn = createDecisionColumn(decision.getDescription());
            decisionsContainer.getChildren().add(decisionColumn);
        }

        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.show();
    }

    private VBox createDecisionColumn(String decision) {
        VBox column = new VBox();
        column.setAlignment(Pos.TOP_CENTER);
        column.setSpacing(10);

        Label decisionLabel = new Label(decision);
        decisionLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/Seagram tfb.ttf"), 13));
        decisionLabel.setTextFill(Color.BLACK);
        decisionLabel.setWrapText(true);
        decisionLabel.setMaxWidth(BUTTON_WIDTH);
        decisionLabel.setTextAlignment(TextAlignment.CENTER);
        VBox.setVgrow(decisionLabel, Priority.ALWAYS);

        Button chooseButton = new Button("X");
        chooseButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        chooseButton.setDisable(false);
        chooseButton.setFont(Font.loadFont(getClass().getResourceAsStream("/Seagram tfb.ttf"), 20));
        VBox.setMargin(chooseButton, new Insets(10, 0, 0, 0));
        VBox.setVgrow(chooseButton, Priority.NEVER);
        chooseButton.setAlignment(Pos.CENTER);

        // Event handler for the chooseButton
        chooseButton.setOnAction(event -> {
            // Retrieve the current event from the EventWindow
            Event currentEvent = this.event;

            // Find the corresponding Decision based on the decision text
            Decision selectedDecision = null;
            if (currentEvent != null) {
                List<Decision> decisions = currentEvent.getDecisions();
                for (Decision decisionObj : decisions) {
                    if (decisionObj.getDescription().equals(decision)) {
                        selectedDecision = decisionObj;
                        break;
                    }
                }
            }

            // Call the decideEvent method in GameScreen and pass the selected Decision
            ResolveEvent resolution = new ResolveEvent(currentEvent, selectedDecision);
        });

        column.getChildren().addAll(decisionLabel, chooseButton);
        return column;
    }

}
