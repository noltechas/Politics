package com.example.politicsgame.Events;

import java.util.List;

public class TestEventReader {
    public static void main(String[] args) {
        List<Event> events = EventReader.readEventsFromJson("src/main/resources/events.json");

        if (events == null) {
            System.out.println("No events were read from the file.");
        } else {
            for (Event event : events) {
                System.out.println("Event Name: " + event.getName());
                System.out.println("Event Description: " + event.getDescription());
                System.out.println("Event Location: " + event.getLocation());

                List<Decision> decisions = event.getDecisions();
                for (Decision decision : decisions) {
                    System.out.println("\tDecision Description: " + decision.getDescription());

                    List<Outcome> outcomes = decision.getOutcomes();
                    for (Outcome outcome : outcomes) {
                        System.out.println("\t\tOutcome Description: " + outcome.getDescription());
                        System.out.println("\t\t\tOutcome Probability: " + outcome.getProbability());

                        Affect kingdomAffects = outcome.getKingdomAffects();
                        System.out.println("\t\t\tKingdom Affects: "
                                + "Positivity: " + kingdomAffects.getPositive()
                                + ", Influence: " + kingdomAffects.getInfluence()
                                + ", Wealth: " + kingdomAffects.getWealth()
                                + ", Happiness: " + kingdomAffects.getHappiness()
                                + ", Food: " + kingdomAffects.getFood()
                                + ", Knowledge: " + kingdomAffects.getKnowledge()
                                + ", Stability: " + kingdomAffects.getStability());

                        Affect cityAffects = outcome.getAffectedCityAffects();
                        System.out.println("\t\t\tAffected City Affects: "
                                + "Positivity: " + cityAffects.getPositive()
                                + ", Influence: " + cityAffects.getInfluence()
                                + ", Wealth: " + cityAffects.getWealth()
                                + ", Happiness: " + cityAffects.getHappiness()
                                + ", Food: " + cityAffects.getFood()
                                + ", Knowledge: " + cityAffects.getKnowledge()
                                + ", Stability: " + cityAffects.getStability());

                        Affect adjacentAffects = outcome.getAdjacent_city_affects();
                        System.out.println("\t\t\tAdjacent City Affects: "
                                + "Positivity: " + adjacentAffects.getPositive()
                                + ", Influence: " + adjacentAffects.getInfluence()
                                + ", Wealth: " + adjacentAffects.getWealth()
                                + ", Happiness: " + adjacentAffects.getHappiness()
                                + ", Food: " + adjacentAffects.getFood()
                                + ", Knowledge: " + adjacentAffects.getKnowledge()
                                + ", Stability: " + adjacentAffects.getStability());
                    }
                }

                System.out.println();
            }
        }
    }
}
