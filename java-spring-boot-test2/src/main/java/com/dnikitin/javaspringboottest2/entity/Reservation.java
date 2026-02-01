package com.dnikitin.javaspringboottest2.entity;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record Reservation(
        Long id,
        Long userId,
        Long roomId,
        LocalDate startDate,
        LocalDate endDate,
        ReservationStatus status
) {
}
