package com.example.politicsgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CityPartySupport {
    private City city;
    private ArrayList<Party> parties;
    private ArrayList<Double> supportPercentages;

    public CityPartySupport(City city, List<Party> parties) {
        this.city = city;
        this.parties = new ArrayList<>(parties);
        this.supportPercentages = new ArrayList<>();

        Random random = new Random();
        int partyCount = parties.size();
        for (int i = 0; i < partyCount; i++) {
            // Assign equal initial support to each party
            this.supportPercentages.add(100.0 / partyCount);
        }

        // Adjust support randomly
        for (int i = 0; i < partyCount; i++) {
            int j = random.nextInt(partyCount);
            double adjustment = this.supportPercentages.get(i) * 0.1 * (random.nextDouble() - 0.5);
            this.supportPercentages.set(i, this.supportPercentages.get(i) - adjustment);
            this.supportPercentages.set(j, this.supportPercentages.get(j) + adjustment);
        }

        // Update support percentages in Party objects
        for (int i = 0; i < partyCount; i++) {
            this.parties.get(i).addCitySupport(city, this.supportPercentages.get(i));
        }
    }

    public City getCity() {
        return city;
    }

    public ArrayList<Party> getParties() {
        return this.parties;
    }

    public List<Double> getSupportPercentages() {
        return supportPercentages;
    }

    // ... other city-party-support-specific methods and properties
}
