package com.example.ticketselling.controller;

import com.example.ticketselling.dto.SeatDto;
import com.example.ticketselling.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public ResponseEntity<List<SeatDto>> retrieveSeats() {
        return ResponseEntity.ok().body(seatService.retrieveSeats());
    }

    @PostMapping("/add")
    public ResponseEntity<SeatDto> addSeat(@RequestBody SeatDto seatDto) {
        return ResponseEntity.ok().body(seatService.saveSeat(seatDto));
    }

    @PutMapping("/edit/{seatId}")
    public ResponseEntity<SeatDto> editSeat(@PathVariable int seatId, @RequestBody SeatDto editedSeatDto) {
        return ResponseEntity.ok().body(seatService.updateSeat(seatId, editedSeatDto));
    }

    @DeleteMapping("/delete/{seatId}")
    public ResponseEntity<String> addSeat(@PathVariable int seatId) {
        seatService.deleteSeatById(seatId);
        return ResponseEntity.ok().body("Seat successfully deleted!");
    }
}
