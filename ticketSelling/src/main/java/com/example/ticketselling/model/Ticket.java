package com.example.ticketselling.model;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
