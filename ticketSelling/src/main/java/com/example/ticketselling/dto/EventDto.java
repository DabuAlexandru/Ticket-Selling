package com.example.ticketselling.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private int id;

    private String description;

    private String name;

    private int duration;
}
