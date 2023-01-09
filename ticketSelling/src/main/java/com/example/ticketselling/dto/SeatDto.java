package com.example.ticketselling.dto;

import com.example.ticketselling.model.Location;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto {

    private int id;

    @NotEmpty(message = "A seat can't exist outside of a location")
    private LocationDto location;

    @NotBlank(message = "A seat should have a comprehensive number")
    private String seatNo;

    private String additionalInfo;
}
