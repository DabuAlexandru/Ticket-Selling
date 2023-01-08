package com.example.ticketselling.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

    private int id;

    private String address;

    private String addressDetails;

    private String name;

    private int maxCapacity;
}
