package com.example.ticketselling.dto;

import com.example.ticketselling.constants.SeatConstants;
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

    @NotEmpty(message = SeatConstants.LOCATION_NOT_EMPTY_CONSTRAINT_MESSAGE)
    private LocationDto location;

    @NotBlank(message = SeatConstants.SEAT_NO_NOT_BLANK_CONSTRAINT_MESSAGE)
    private String seatNo;

    private String additionalInfo;
}
