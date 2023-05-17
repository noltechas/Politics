package com.example.politicsgame;

import com.example.politicsgame.Events.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EventWindow {
    private static final double WINDOW_WIDTH = 600;
    private static final double WINDOW_HEIGHT = 400;

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

        // Load custom font
        Font ancientFont = Font.loadFont(getClass().getResourceAsStream("/Deutsch.ttf"), 20);

        // Event description
        Label descriptionLabel;
        if(event.getLocation() != "whole_kingdom")
            descriptionLabel = new Label(event.getDescription() + event.getLocation());
        else
            descriptionLabel = new Label(event.getDescription());
        descriptionLabel.setFont(Font.font(ancientFont.getFamily(), FontWeight.BOLD, 20));
        descriptionLabel.setTextFill(Color.BLACK);
        descriptionLabel.setWrapText(true); // Enable text wrapping
        descriptionLabel.setMaxWidth(WINDOW_WIDTH - 20); // Adjust width for padding
        vbox.getChildren().add(descriptionLabel);

        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.show();
    }
}
