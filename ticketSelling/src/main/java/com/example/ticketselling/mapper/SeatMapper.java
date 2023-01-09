package com.example.ticketselling.mapper;

import com.example.ticketselling.dto.SeatDto;
import com.example.ticketselling.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class SeatMapper {

    private final LocationMapper locationMapper;

    @Autowired
    public SeatMapper(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }

    public Seat convertFromDto(SeatDto seatDto) {
        if(isNull(seatDto)) {
            return null;
        }

        return new Seat(
                seatDto.getId(),
                seatDto.getAdditionalInfo(),
                seatDto.getSeatNo(),
                locationMapper.convertFromDto(seatDto.getLocation())
        );
    }

    public SeatDto convertToDto(Seat seat) {
        if(isNull(seat)) {
            return null;
        }

        return new SeatDto(
                seat.getId(),
                locationMapper.convertToDto(seat.getLocation()),
                seat.getSeatNo(),
                seat.getAdditionalInfo()
        );
    }
}
