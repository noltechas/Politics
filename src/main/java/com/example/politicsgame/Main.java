package com.example.politicsgame;

import static javafx.application.Application.launch;

public class Main {

    /*TODO:
        1. Create Event handling for player/all parties
        2. Create Game Loop
        3. Create equations for handling positive/negative events
     */
    public static Party party;

    public static void main(String[] args) {
        launch(SetupScreen.class, args);
    }
}
