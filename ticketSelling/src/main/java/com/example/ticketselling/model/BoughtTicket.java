package com.example.ticketselling.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "boughtTicket")
public class BoughtTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private Date boughtAt;


    public BoughtTicket() {
    }

    public BoughtTicket(int id, Ticket ticket, Client client, Date boughtAt) {
        this.id = id;
        this.ticket = ticket;
        this.client = client;
        this.boughtAt = boughtAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getBoughtAt() {
        return boughtAt;
    }

    public void setBoughtAt(Date boughtAt) {
        this.boughtAt = boughtAt;
    }

    @Override
    public String toString() {
        return "BoughtTicket{" +
                "id=" + id +
                ", ticket=" + ticket +
                ", client=" + client +
                '}';
    }
}