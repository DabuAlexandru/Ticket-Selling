package com.example.ticketselling.dto;

import com.example.ticketselling.constants.LocationConstants;
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

    @NotBlank(message = LocationConstants.ADDRESS_NOT_BLANK_CONSTRAINT_MESSAGE)
    private String address;

    private String addressDetails;

    @NotBlank(message = LocationConstants.NAME_NOT_BLANK_CONSTRAINT_MESSAGE)
    private String name;

    @Min(value = LocationConstants.MAX_CAPACITY_MIN_CONSTRAINT_VALUE, message = LocationConstants.MAX_CAPACITY_MIN_CONSTRAINT_MESSAGE)
    private int maxCapacity;
}
