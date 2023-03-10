package com.example.ticketselling.model;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
