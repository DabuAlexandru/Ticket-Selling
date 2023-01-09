package com.example.ticketselling.dto;

import lombok.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

    private int id;

    @NotBlank(message = "A location should have a valid address")
    private String address;

    private String addressDetails;

    @NotBlank(message = "A location should have a valid name")
    private String name;

    @Min(value=25, message="A valid event location should have at least 25 designed seats")
    private int maxCapacity;
}
