package com.example.ticketselling.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private int id;

    @NotBlank(message="firstName is required for a valid client")
    private String firstName;

    @NotBlank(message="lastName is required for a valid client")
    private String lastName;

    @NotBlank(message="email is required for a valid client")
    private String email;

    @NotBlank(message="phoneNumber is required for a valid client")
    private String phoneNumber;

    @NotNull(message="dateOfBirth is required for a valid client")
    private Date dateOfBirth;
}
