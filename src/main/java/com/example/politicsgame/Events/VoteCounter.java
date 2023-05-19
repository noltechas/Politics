package com.example.politicsgame.Events;

import com.example.politicsgame.GameMap;
import com.example.politicsgame.Main;
import com.example.politicsgame.Vote;

import java.util.ArrayList;

public class VoteCounter {

    private Event event;
    private Decision playerDecision;
    private ArrayList<Vote> votes;

    public VoteCounter(Event event, Decision playerDecision, ArrayList<Vote> votes){
        this.event = event;
        this.playerDecision = playerDecision;
        this.votes = votes;
    }

    public Decision kingdomVote(){

        // Make decisions
        for(int i = 0; i < GameMap.parties.size(); i++){
            // If the party is not the player's party
            if(!GameMap.parties.get(i).getName().equals(Main.party.getName())){
                // Choose a random decision to choose
            }
        }

        // Count Votes
        for(int i = 0; i < GameMap.getCities().size(); i++){
            for(int j = 0; j < GameMap.parties.size(); j++){

            }
        }

        return null;
    }

    public ArrayList<Decision> cityVote() {

        return null;
    }
}
