package com.example.ticketselling.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "eventPlanning")
public class EventPlanning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private Date startDate;

    public EventPlanning() {
    }

    public EventPlanning(Location location, Event event, Date startDate) {
        this.location = location;
        this.event = event;
        this.startDate = startDate;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "EventPlanning{" +
                "id=" + id +
                ", location=" + location +
                ", event=" + event +
                ", startDate=" + startDate +
                '}';
    }
}
