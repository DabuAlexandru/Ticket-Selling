package com.example.ticketselling.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private String key;

    /**
     some event locations can have rows, levels
     the additional key provides an extra layer of separation for clarity
     */
    private String additionalKey;

    public Seat(Location location, String key, String additionalKey) {
        this.location = location;
        this.key = key;
        this.additionalKey = additionalKey;
    }

    public Seat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAdditionalKey() {
        return additionalKey;
    }

    public void setAdditionalKey(String additionalKey) {
        this.additionalKey = additionalKey;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", location=" + location +
                ", key='" + key + '\'' +
                ", additionalKey='" + additionalKey + '\'' +
                '}';
    }
}
