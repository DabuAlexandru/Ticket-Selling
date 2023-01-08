package com.example.ticketselling.model;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
