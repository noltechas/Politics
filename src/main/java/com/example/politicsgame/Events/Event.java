package com.example.politicsgame.Events;

import com.example.politicsgame.Party;

import java.util.ArrayList;
import java.util.Collections;

public class Event {
    private String name;
    private String description;
    private String location;
    private String cityName;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public ArrayList<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(ArrayList<Decision> decisions) {
        this.decisions = decisions;
    }

    public Decision makeDecision(){
        ArrayList<Decision> oddsDecisions = new ArrayList<>(decisions);
        Collections.shuffle(oddsDecisions);
        return oddsDecisions.get(0);
    }
}
