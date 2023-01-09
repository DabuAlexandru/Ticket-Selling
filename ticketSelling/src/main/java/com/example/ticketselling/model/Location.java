package com.example.ticketselling.model;

import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    private String addressDetails;

    private String name;

    private int maxCapacity;
//
//    @OneToMany(
//            mappedBy = "location",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<Seat> seats = new ArrayList<>();
}
