package com.example.politicsgame.Events;

public class Outcome {
    private String description;
    private int probability;
    private Affect kingdom_affects;
    private Affect affected_city_affects;
    private boolean isPositive;

    public Affect getAdjacent_city_affects() {
        return adjacent_city_affects;
    }

    public void setAdjacent_city_affects(Affect adjacent_city_affects) {
        this.adjacent_city_affects = adjacent_city_affects;
    }

    private Affect adjacent_city_affects;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    public Affect getKingdomAffects() {
        return kingdom_affects;
    }

    public void setKingdomAffects(Affect kingdom_affects) {
        this.kingdom_affects = kingdom_affects;
    }

    public Affect getAffectedCityAffects() {
        return affected_city_affects;
    }

    public void setAffectedCityAffects(Affect affected_city_affects) {
        this.affected_city_affects = affected_city_affects;
    }

    public boolean isPositive() {
        return isPositive;
    }

}