package com.example.politicsgame;

public class GameState {

    private static int Generation = 1;

    private Party playerParty;

    public GameState(){

    }

    public void setPlayerParty(Party party){
        this.playerParty = party;
    }

    public static int getGeneration(){
        return Generation;
    }
}
