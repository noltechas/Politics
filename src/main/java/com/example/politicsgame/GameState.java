package com.example.politicsgame;

import java.util.Random;

public class GameState {

    private static int generation = 1;
    private static Party playerParty;
    private static boolean playerTurn = true;
    private static boolean eventActive = false;

    public GameState() {
    }

    public static void setPlayerParty(Party party) {
        playerParty = party;
    }

    public static int getGeneration() {
        return generation;
    }

    public static boolean getPlayerTurn() {
        return playerTurn;
    }

    public static void endPlayerTurn() {
        playerTurn = false;
    }

    public static void startPlayerTurn() {
        playerTurn = true;
        eventActive = false;
    }

    public static boolean isEventActive() {
        return eventActive;
    }

    public static void activateEvent() {
        eventActive = true;
    }

    public static void resolveEvent() {
        eventActive = false;
    }

    public static boolean shouldTriggerEvent() {
        // Logic to determine if an event should be triggered based on game conditions
        Random random = new Random();
        int chance = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        return true; // 20% chance of triggering an event (adjust the value as desired)
    }
}
