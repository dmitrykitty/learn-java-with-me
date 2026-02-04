package com.dnikitin.javaspringboottest2.services;

import lombok.Builder;

@Builder
public record ReservationFilter(
        Long roomId,
        Long userId,
        Integer pageSize,
        Integer pageNumber
) {
}
