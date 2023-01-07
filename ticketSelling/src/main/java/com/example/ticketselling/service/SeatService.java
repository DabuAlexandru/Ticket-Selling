package com.example.ticketselling.service;

import com.example.ticketselling.model.Location;
import com.example.ticketselling.model.Seat;
import com.example.ticketselling.repository.LocationRepository;
import com.example.ticketselling.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class SeatService {
    private final SeatRepository seatRepository;
    private final LocationRepository locationRepository;

    public SeatService(SeatRepository seatRepository,
                       LocationRepository locationRepository) {
        this.seatRepository = seatRepository;
        this.locationRepository = locationRepository;
    }

    public List<Seat> retrieveSeats() {
        return seatRepository.findAll();
    }

    public Seat findSeatById(int SeatId) {
        return seatRepository.findById(SeatId).orElseThrow(() -> new RuntimeException("Seat not found!"));
    }

    public Seat updateSeat(int SeatId, Seat updatedSeat) {
        Seat seat = seatRepository.findById(SeatId).orElseThrow(() -> new RuntimeException("Seat not found!"));
        if (!isNull(updatedSeat.getKey())) {
            seat.setKey(updatedSeat.getKey());
        }
        if (!isNull(updatedSeat.getAdditionalKey())) {
            seat.setAdditionalKey(updatedSeat.getAdditionalKey());
        }
        if (!isNull(updatedSeat.getLocation())) {
            Location location = locationRepository.findById(updatedSeat.getLocation().getId()).orElseThrow(() -> new RuntimeException("Location not found!"));
            seat.setLocation(location);
        }
        return seatRepository.save(seat);
    }

    public Seat saveSeat(Seat seat) {
        Location location = locationRepository.findById(seat.getLocation().getId()).orElseThrow(() -> new RuntimeException("Location not found!"));

        seat.setLocation(location);

        return seatRepository.save(seat);
    }

    public void deleteSeatById(Integer seatId) {
        seatRepository.deleteById(seatId);
    }
}
