package com.example.politicsgame.Events;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EventReader {

    public static ArrayList<Event> readEventsFromJson(String filename) {
        Gson gson = new Gson();

        try (JsonReader reader = new JsonReader(new FileReader(filename))) {
            EventListWrapper eventListWrapper = gson.fromJson(reader, EventListWrapper.class);
            return new ArrayList<>(eventListWrapper.getEvents());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
