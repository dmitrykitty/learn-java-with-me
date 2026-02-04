package com.dnikitin.javaspringboottest2.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record Reservation(
        @Null
        Long id,

        @NotNull
        Long userId,

        @NotNull
        Long roomId,

        @NotNull
        @FutureOrPresent
        LocalDate startDate,

        @NotNull
        @FutureOrPresent
        LocalDate endDate,

        ReservationStatus status
) {
}
