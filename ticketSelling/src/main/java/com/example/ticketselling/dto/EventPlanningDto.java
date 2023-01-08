package com.example.ticketselling.dto;

import com.example.ticketselling.model.Location;
import lombok.*;

import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventPlanningDto {

    private int id;

    private LocationDto location;

    private EventDto event;

    private Date startDate;
}
