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
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Date dateOfBirth;
}
