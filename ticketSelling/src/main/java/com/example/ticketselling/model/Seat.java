package com.example.ticketselling.model;

import javax.persistence.*;
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

    private String seatNo;

    private String additionalInfo;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
