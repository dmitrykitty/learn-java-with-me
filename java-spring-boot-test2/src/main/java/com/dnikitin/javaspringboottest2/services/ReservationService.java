package com.dnikitin.javaspringboottest2.services;

import com.dnikitin.javaspringboottest2.entity.Reservation;
import com.dnikitin.javaspringboottest2.entity.ReservationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationService {

    public Reservation getReservationById(Long id) {
        return Reservation.builder()
                .id(id)
                .roomId(10L)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(5))
                .status(ReservationStatus.APPROVED)
                .build();
    }

}
