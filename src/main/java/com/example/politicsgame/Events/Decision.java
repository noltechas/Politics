package com.example.politicsgame.Events;

import java.util.ArrayList;

public class Decision {
    private String description;
    private ArrayList<Outcome> outcomes;

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

    // Get the support adjustment value based on the decision's outcomes
    public double getSupportAdjustment() {
        double supportAdjustment = 0.0;
        for (Outcome outcome : outcomes) {
            if (outcome.isPositive()) {
                supportAdjustment += 5;
            } else {
                supportAdjustment -= 5;
            }
        }
        return supportAdjustment;
    }
}