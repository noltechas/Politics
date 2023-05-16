package com.example.politicsgame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CityStatsDisplay {
    private City city;

    public CityStatsDisplay(City city) {
        this.city = city;
    }

    public void displayCityStats() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("City Stats");

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setStyle("-fx-background-color: #F5DEB3;");

        // Load custom fonts
        Font OldLondon = null;
        Font Seagram = null;
        Font Deutsch = null;
        try {
            OldLondon = Font.loadFont(new FileInputStream("src/main/resources/OldLondon.ttf"), 60);
            Seagram = Font.loadFont(new FileInputStream("src/main/resources/Seagram tfb.ttf"), 32);
            Deutsch = Font.loadFont(new FileInputStream("src/main/resources/Deutsch.ttf"), 20);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Text cityName;
        // Header
        if(!city.isCapitol())
            cityName = new Text(city.getName());
        else
            cityName = new Text("Capitol City of " + city.getName());
        cityName.setFont(OldLondon);
        cityName.setFill(Color.DARKGRAY);
        pane.setTop(cityName);
        BorderPane.setAlignment(cityName, Pos.CENTER);

        // City stats
        VBox statsBox = new VBox();
        statsBox.setSpacing(10);

        Label population = new Label("Population: " + city.getPopulation());
        population.setFont(Seagram);
        population.setTextFill(Color.DARKGREEN);
        statsBox.getChildren().add(population);

        Label money = new Label("Money: $" + city.getMoney());
        money.setFont(Seagram);
        money.setTextFill(Color.DARKGREEN);
        statsBox.getChildren().add(money);

        Label happiness = new Label("Happiness: " + city.getHappiness());
        happiness.setFont(Seagram);
        happiness.setTextFill(Color.DARKGREEN);
        statsBox.getChildren().add(happiness);

        Label resources = new Label("Resources: " + city.getResources().get(0) + " and " + city.getResources().get(1));
        resources.setFont(Seagram);
        resources.setTextFill(Color.DARKGREEN);
        statsBox.getChildren().add(resources);

        // Party stats
        VBox partyStatsBox = new VBox();
        partyStatsBox.setSpacing(10);

        ArrayList<Party> topParties = (ArrayList<Party>) city.getTopParties(15);
        int counter = 0;
        for (Party party : topParties) {
            double support = party.getSupportPercentage(city);
            String formattedSupport = String.format("%.1f", support);
            Label partyLabel = new Label(party.getName() + ": " + formattedSupport + "%");
            partyLabel.setFont(Deutsch);
            if(counter == 0)
                partyLabel.setTextFill(Color.GOLDENROD);
            else
                partyLabel.setTextFill(party.getColor());
            partyStatsBox.getChildren().add(partyLabel);
            counter++;
        }

        pane.setRight(partyStatsBox);


        pane.setCenter(statsBox);

        Scene scene = new Scene(pane);
        window.setScene(scene);
        window.showAndWait();
    }
}
