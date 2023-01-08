package com.example.ticketselling.dto;

import com.example.ticketselling.model.Location;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto {

    private int id;

    private LocationDto location;

    private String key;

    private String additionalKey;
}
