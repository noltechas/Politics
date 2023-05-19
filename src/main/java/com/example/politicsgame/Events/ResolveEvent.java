package com.example.politicsgame.Events;

import com.example.politicsgame.City;
import com.example.politicsgame.GameMap;
import com.example.politicsgame.Main;
import com.example.politicsgame.Party;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class ResolveEvent {

    private Decision winningDecision;
    private Outcome winningOutcome;
    public ResolveEvent(Event event, Decision playerDecision) {
        System.out.println("Player decision: " + playerDecision.getDescription());
        // Make and count all the decisions
        for(int i = 0; i < GameMap.parties.size(); i++){
            // Make a decision for all the non-player parties
            if(!GameMap.parties.get(i).playerParty()){
                Random random = new Random();
                int randomIndex = random.nextInt(event.getDecisions().size());
                Decision randomDecision = event.getDecisions().get(randomIndex);
                GameMap.parties.get(i).setCurrentDecision(randomDecision);
            }
            // Add the player's decision to their party
            else{
                GameMap.parties.get(i).setCurrentDecision(playerDecision);
            }
        }

        // See which decision wins
        for(int i = 0; i < GameMap.parties.size(); i++){
            GameMap.parties.get(i).getCurrentDecision().addVotes(GameMap.parties.get(i).getSupporters());
        }

        for(int i = 0; i < event.getDecisions().size(); i++)
            System.out.println(event.getDecisions().get(i).getDescription() + ": " + event.getDecisions().get(i).getVotes());

        // If the entire kingdom will have the same outcome
        if(!Objects.equals(event.getLocation(), "whole_kingdom")){
            // Calculate the winning decision
            winningDecision = event.getDecisions().get(0);
            for(int i = 1; i < event.getDecisions().size(); i++){
                if(event.getDecisions().get(i).getVotes() > winningDecision.getVotes())
                    winningDecision = event.getDecisions().get(i);
            }

            System.out.println("The kingdom has decided to " + winningDecision.getDescription());

            ArrayList<Outcome> possibleOutcomes = new ArrayList<>();
            for(int i = 0; i < winningDecision.getOutcomes().size(); i++){
                for(int j = 0; j < winningDecision.getOutcomes().get(i).getProbability(); j++)
                    possibleOutcomes.add(winningDecision.getOutcomes().get(i));
            }

            Random random = new Random();
            int randomIndex = random.nextInt(winningDecision.getOutcomes().size());
            winningOutcome = winningDecision.getOutcomes().get(randomIndex);

            System.out.println("The outcome is: " + winningOutcome.getDescription());
            System.out.println("The affect of this is: " + winningOutcome.getKingdomAffects().getPositive());

            // Adjust party support based on outcome
            for(int i = 0; i < GameMap.parties.size(); i++){
                if(GameMap.parties.get(i).getCurrentDecision() == winningDecision){
                    for(int j = 0; j < GameMap.getCities().size(); j++){
                        GameMap.getCities().get(j).updatePartySupport(GameMap.parties.get(i),winningOutcome.getKingdomAffects().getPositive());
                    }
                }
            }

            // Scale support percentages
            for(int i = 0; i < GameMap.getCities().size(); i++){
                GameMap.getCities().get(i).scalePartySupportPercentages();
            }

        }
    }

}
