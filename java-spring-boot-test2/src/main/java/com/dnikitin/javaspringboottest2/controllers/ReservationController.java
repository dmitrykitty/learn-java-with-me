package com.dnikitin.javaspringboottest2.controllers;

import com.dnikitin.javaspringboottest2.entity.Reservation;
import com.dnikitin.javaspringboottest2.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(
            @PathVariable Long id) {
        return reservationService.getReservationById(id);
    }
}
