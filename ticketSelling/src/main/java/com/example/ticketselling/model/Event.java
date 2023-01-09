package com.example.ticketselling.model;

import javax.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private String name;

    /**
     * Duration in minutes
     */
    private int duration;
}
