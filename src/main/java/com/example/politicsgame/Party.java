package com.example.politicsgame;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Party {
    private String name;
    private Map<City, Double> citySupport;
    private Color color;

    public Party(String name) {
        this.name = name;
        this.citySupport = new HashMap<>();
        this.color = generateRandomColor();
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
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return Color.color(r, g, b);
    }

    // Get the color of this party
    public Color getColor() {
        return color;
    }
}
