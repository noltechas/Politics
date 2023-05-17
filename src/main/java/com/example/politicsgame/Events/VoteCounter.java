package com.example.politicsgame.Events;

import com.example.politicsgame.City;
import com.example.politicsgame.Events.Decision;
import com.example.politicsgame.Events.Event;
import com.example.politicsgame.GameMap;
import com.example.politicsgame.Party;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class VoteCounter {

    public static Decision countVotes(Event event, Map<Party, Decision> partyDecisions) {
        if (event.getLocation().equals("whole_kingdom")) {
            // Handle kingdom-wide event
            return countKingdomWideVotes(event, partyDecisions);
        } else {
            // Handle single-city event
            return countSingleCityVotes(event, partyDecisions);
        }
    }

    private static Decision countKingdomWideVotes(Event event, Map<Party, Decision> partyDecisions) {
        // Map to store the total votes for each decision
        Map<Decision, Integer> decisionVotes = new HashMap<>();

        // Iterate over the parties' decisions
        for (Map.Entry<Party, Decision> entry : partyDecisions.entrySet()) {
            Party party = entry.getKey();
            Decision decision = entry.getValue();

            // Calculate the votes based on the party's support percentage and city population
            List<City> cities = GameMap.getCities();
            for (City city : cities) {
                double supportPercentage = party.getSupportPercentage(city);
                int cityPopulation = city.getPopulation();
                int votes = (int) Math.round(supportPercentage * cityPopulation);

                // Update the decision's total votes
                decisionVotes.put(decision, decisionVotes.getOrDefault(decision, 0) + votes);
            }
        }

        // Find the decision with the most votes (plurality)
        Decision winningDecision = null;
        int maxVotes = 0;
        for (Map.Entry<Decision, Integer> entry : decisionVotes.entrySet()) {
            Decision decision = entry.getKey();
            int votes = entry.getValue();
            if (votes > maxVotes) {
                winningDecision = decision;
                maxVotes = votes;
            }
        }

        return winningDecision;
    }

    private static Decision countSingleCityVotes(Event event, Map<Party, Decision> partyDecisions) {
        // Get the single city for the event
        City city = event.getCity();

        // Map to store the total votes for each decision
        Map<Decision, Integer> decisionVotes = new HashMap<>();

        // Iterate over the parties' decisions
        for (Map.Entry<Party, Decision> entry : partyDecisions.entrySet()) {
            Party party = entry.getKey();
            Decision decision = entry.getValue();

            // Calculate the votes based on the party's support percentage and city population
            double supportPercentage = party.getSupportPercentage(city);
            int cityPopulation = city.getPopulation();
            int votes = (int) Math.round(supportPercentage * cityPopulation);

            // Update the decision's total votes
            decisionVotes.put(decision, votes);
        }

        // Find the decision with the most votes (plurality)
        Decision winningDecision = null;
        int maxVotes = 0;
        for (Map.Entry<Decision, Integer> entry : decisionVotes.entrySet()) {
            Decision decision = entry.getKey();
            int votes = entry.getValue();
            if (votes > maxVotes) {
                winningDecision = decision;
                maxVotes = votes;
            }
        }

        return winningDecision;
    }

}
