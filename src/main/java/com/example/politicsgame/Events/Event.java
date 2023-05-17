package com.example.politicsgame.Events;

import com.example.politicsgame.City;
import com.example.politicsgame.GameMap;

import java.util.ArrayList;

public class Event {
    private String name;
    private String description;
    private String location;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

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

    public ArrayList<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(ArrayList<Decision> decisions) {
        this.decisions = decisions;
    }

    public City getCity(){
        for(int i = 0; i < GameMap.getCities().size(); i++){
            if(GameMap.getCities().get(i).getName().equals(this.location))
                return GameMap.getCities().get(i);
        }
        System.out.println("ERROR IN FINDING CITY");
        return null;
    }
}