package com.example.ticketselling.service;

import com.example.ticketselling.constants.LocationConstants;
import com.example.ticketselling.constants.SeatConstants;
import com.example.ticketselling.dto.SeatDto;
import com.example.ticketselling.mapper.SeatMapper;
import com.example.ticketselling.model.Location;
import com.example.ticketselling.model.Seat;
import com.example.ticketselling.repository.LocationRepository;
import com.example.ticketselling.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class SeatService {
    private final SeatRepository seatRepository;
    private final LocationRepository locationRepository;

    private final SeatMapper seatMapper;

    public SeatService(SeatRepository seatRepository, LocationRepository locationRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.locationRepository = locationRepository;
        this.seatMapper = seatMapper;
    }

    public List<SeatDto> retrieveSeats() {
        return seatRepository.findAll().stream()
                .map(seatMapper::convertToDto)
                .collect(Collectors.toList());
    }

//    public List<SeatDto> retrieveSeatsByLocationId(int locationId) {
//        return seatRepository.findAll().stream()
//                .filter(seat -> seat.getLocation().getId() == locationId)
//                .map(seatMapper::convertToDto)
//                .collect(Collectors.toList());
//    }

    public SeatDto updateSeat(int SeatId, SeatDto updatedSeatDto) {
        Seat seat = seatRepository.findById(SeatId).orElseThrow(() -> new RuntimeException(SeatConstants.SEAT_NOT_FOUND_MESSAGE));
        if (!isNull(updatedSeatDto.getSeatNo())) {
            seat.setSeatNo(updatedSeatDto.getSeatNo());
        }
        if (!isNull(updatedSeatDto.getAdditionalInfo())) {
            seat.setAdditionalInfo(updatedSeatDto.getAdditionalInfo());
        }
        if (!isNull(updatedSeatDto.getLocation())) {
            Location location = locationRepository.findById(updatedSeatDto.getLocation().getId()).orElseThrow(() -> new RuntimeException(LocationConstants.LOCATION_NOT_FOUND_MESSAGE));
            seat.setLocation(location);
        }
        return seatMapper.convertToDto(seatRepository.save(seat));
    }

    public SeatDto saveSeat(SeatDto seatDto) {
        Seat seat = seatMapper.convertFromDto(seatDto);

        if(!isNull(seatDto.getLocation())) {
            Location location = locationRepository.findById(seatDto.getLocation().getId()).orElseThrow(() -> new RuntimeException("Location not found!"));
            seat.setLocation(location);
        }

        return seatMapper.convertToDto(seatRepository.save(seat));
    }

    public void deleteSeatById(Integer seatId) {
        seatRepository.deleteById(seatId);
    }
}
