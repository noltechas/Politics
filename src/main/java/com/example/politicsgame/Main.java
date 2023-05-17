package com.example.politicsgame;

import static javafx.application.Application.launch;

public class Main {

    /*TODO:
        1. Create text field
        2. Create Event handling for player/all parties
        3. Create Game Loop
        4. Create "Next Turn" button (only available at certain times)
        5. Create equations for handling positive/negative events
        6. Add adjacent_city_affects to the json format
     */
    public static Party party;

    public static void main(String[] args) {
        launch(SetupScreen.class, args);
    }
}
