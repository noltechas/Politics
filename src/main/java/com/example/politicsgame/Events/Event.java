package com.example.politicsgame.Events;

import java.util.ArrayList;

public class Event {
    private String name;
    private String description;
    private String location;
    private ArrayList<Decision> decisions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(ArrayList<Decision> decisions) {
        this.decisions = decisions;
    }
}