package com.example.politicsgame.Events;

import java.util.ArrayList;

public class Decision {
    private String description;
    private int votes = 0;
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

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void addVotes(int votes) {
        this.votes += votes;
    }
}