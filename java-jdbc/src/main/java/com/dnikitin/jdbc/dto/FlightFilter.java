package com.dnikitin.jdbc.dto;

import java.time.LocalDateTime;

public record FlightFilter(int limit,
                           int offset,
                           String flightNo,
                           LocalDateTime departureDate,
                           LocalDateTime arrivalDate) implements Filter {
}
