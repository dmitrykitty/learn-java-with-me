package com.dnikitin.jdbc.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FlightEntity {
    private Long id;
    private String flightNo;
    private LocalDateTime departureDate;
    private String departureAirportCode;
    private LocalDateTime arrivalDate;
    private String arrivalAirportCode;
    private Status status;

    @Builder
    public FlightEntity(String flightNo, LocalDateTime departureDate, String departureAirportCode,
                        LocalDateTime arrivalDate, String arrivalAirportCode, Status status) {
        this.flightNo = flightNo;
        this.departureDate = departureDate;
        this.departureAirportCode = departureAirportCode;
        this.arrivalDate = arrivalDate;
        this.arrivalAirportCode = arrivalAirportCode;
        this.status = status;
    }
}
