package com.example.ticketselling.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ticketType")
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private float price;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public TicketType(String title, float price, Event event) {
        this.title = title;
        this.price = price;
        this.event = event;
    }

    public TicketType() {
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketType{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
