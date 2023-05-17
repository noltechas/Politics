package com.example.politicsgame;

public class GameState {

    private static int Generation = 1;

    private static Party playerParty;

    private static boolean playerTurn = true;

    public GameState(){

    }

    public static void setPlayerParty(Party party){
        playerParty = party;
    }

    public static int getGeneration(){
        return Generation;
    }

    public static boolean getPlayerTurn() {
        return playerTurn;
    }
}
