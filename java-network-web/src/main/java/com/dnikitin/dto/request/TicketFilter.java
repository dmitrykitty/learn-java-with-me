package com.dnikitin.dto.request;

public record TicketFilter(int limit,
                           int offset,
                           String passengerName,
                           String seatNo,
                           Long flightId) implements Filter {
}
