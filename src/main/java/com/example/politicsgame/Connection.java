package com.example.politicsgame;

public class Connection {
    private City city;
    private String connectionType;

    public Connection(City city, String connectionType) {
        this.city = city;
        this.connectionType = connectionType;
    }

    public City getCity() {
        return city;
    }

    public String getConnectionType() {
        return connectionType;
    }
}