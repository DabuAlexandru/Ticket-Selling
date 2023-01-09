package com.example.ticketselling.model;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
