package com.example.politicsgame;

import com.example.politicsgame.Events.Decision;
import com.example.politicsgame.Events.Event;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Party {
    private String name;
    private Map<City, Double> citySupport;
    private Color color;
    private boolean isPlayerParty;

    public Party(String name, boolean isPlayerParty) {
        this.name = name;
        this.citySupport = new HashMap<>();
        this.color = generateRandomColor();
        this.isPlayerParty = isPlayerParty;
    }

    public String getName() {
        return name;
    }

    // Add a city with its support percentage for this party
    public void addCitySupport(City city, double supportPercentage) {
        citySupport.put(city, supportPercentage);
    }

    // Get the support percentage of this party in a city
    public double getSupportPercentage(City city) {
        return citySupport.getOrDefault(city, 0.0);
    }

    // Generate a random color for this party
    private Color generateRandomColor() {
        Random random = new Random();
        float minBrightness = 0.2f; // Minimum brightness value
        float maxBrightness = 0.9f; // Maximum brightness value

        float r, g, b;
        do {
            r = random.nextFloat();
            g = random.nextFloat();
            b = random.nextFloat();
        } while (calculateBrightness(r, g, b) < minBrightness || calculateBrightness(r, g, b) > maxBrightness);

        return Color.color(r, g, b);
    }

    private float calculateBrightness(float r, float g, float b) {
        return (0.299f * r + 0.587f * g + 0.114f * b);
    }

    // Get the color of this party
    public Color getColor() {
        return color;
    }

    // Choose a decision for an event
    public Decision chooseDecision(Event event) {
        if (isPlayerParty) {
            // Perform actions for player party's decision
            // This method should be implemented separately based on your game logic
            // Return the chosen decision for the player
            return null;
        } else {
            // Randomly choose a decision for non-player parties
            ArrayList<Decision> decisions = event.getDecisions();
            int randomIndex = new Random().nextInt(decisions.size());
            return decisions.get(randomIndex);
        }
    }
}
