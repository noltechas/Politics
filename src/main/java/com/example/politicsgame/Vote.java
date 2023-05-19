package com.example.politicsgame;


import com.example.politicsgame.Events.Decision;

public class Vote {
    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    private Decision decision;
    private Party party;

    public Vote(Party party, Decision decision){
        this.party = party;
        this.decision = decision;
    }
}
