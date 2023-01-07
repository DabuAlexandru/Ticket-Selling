package com.example.ticketselling.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @OneToOne
    @JoinColumn(name = "event_planning_id")
    private EventPlanning eventPlanning;

    @OneToOne
    @JoinColumn(name = "ticket_type_id")
    private TicketType ticketType;

    public Ticket(Seat seat, EventPlanning eventPlanning, TicketType ticketType) {
        this.seat = seat;
        this.eventPlanning = eventPlanning;
        this.ticketType = ticketType;
    }

    public Ticket() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public EventPlanning getEventPlanning() {
        return eventPlanning;
    }

    public void setEventPlanning(EventPlanning eventPlanning) {
        this.eventPlanning = eventPlanning;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seat=" + seat +
                ", eventPlanning=" + eventPlanning +
                ", ticketType=" + ticketType +
                '}';
    }
}
