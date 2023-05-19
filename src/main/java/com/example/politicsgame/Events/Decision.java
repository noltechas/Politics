package com.example.politicsgame.Events;

import java.util.ArrayList;

public class Decision {
    private String description;
    private ArrayList<Outcome> outcomes = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Outcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(ArrayList<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

}