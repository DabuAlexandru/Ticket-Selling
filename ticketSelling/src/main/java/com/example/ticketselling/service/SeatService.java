package com.example.ticketselling.service;

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

    public SeatDto findSeatById(int SeatId) {
        Seat seat = seatRepository.findById(SeatId).orElseThrow(() -> new RuntimeException("Seat not found!"));
        return seatMapper.convertToDto(seat);
    }

    public SeatDto updateSeat(int SeatId, SeatDto updatedSeatDto) {
        Seat seat = seatRepository.findById(SeatId).orElseThrow(() -> new RuntimeException("Seat not found!"));
        if (!isNull(updatedSeatDto.getSeatNo())) {
            seat.setSeatNo(updatedSeatDto.getSeatNo());
        }
        if (!isNull(updatedSeatDto.getAdditionalInfo())) {
            seat.setAdditionalInfo(updatedSeatDto.getAdditionalInfo());
        }
        if (!isNull(updatedSeatDto.getLocation())) {
            Location location = locationRepository.findById(updatedSeatDto.getLocation().getId()).orElseThrow(() -> new RuntimeException("Location not found!"));
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
