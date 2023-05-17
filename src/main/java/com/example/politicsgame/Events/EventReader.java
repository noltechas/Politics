package com.example.politicsgame.Events;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class EventReader {

    public static List<Event> readEventsFromJson(String filename) {
        Gson gson = new Gson();

        try (JsonReader reader = new JsonReader(new FileReader(filename))) {
            EventListWrapper eventListWrapper = gson.fromJson(reader, EventListWrapper.class);
            return eventListWrapper.getEvents();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

