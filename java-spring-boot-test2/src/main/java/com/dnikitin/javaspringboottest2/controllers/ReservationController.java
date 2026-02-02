package com.dnikitin.javaspringboottest2.controllers;

import com.dnikitin.javaspringboottest2.dto.Reservation;
import com.dnikitin.javaspringboottest2.services.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(
            @PathVariable Long id) {
        log.info("Get Reservation with id {}", id);
        return ResponseEntity.ok(reservationService.findReservationById(id));
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        log.info("Get all Reservations");
        return ResponseEntity.ok(reservationService.findAllReservations());
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        log.info("Create Reservation {}", reservation);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("header", "tests123")
                .body(reservationService.saveReservation(reservation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable Long id,
            @RequestBody Reservation reservation) {
        log.info("Update Reservation with id {}", id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationService.updateReservation(id, reservation));
    }
}
