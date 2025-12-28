package com.dnikitin.jdbc.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//maping to the row in the table Ticket
@Data
@NoArgsConstructor
public class TicketEntity {
    private Long id;
    private String passengerNo;
    private String passengerName;
    private Long flightId; //FK
    private String seatNo;
    private BigDecimal cost;

    @Builder
    public TicketEntity(String passengerNo, String passengerName, Long flightId, String seatNo, BigDecimal cost) {
        this.passengerNo = passengerNo;
        this.passengerName = passengerName;
        this.flightId = flightId;
        this.seatNo = seatNo;
        this.cost = cost;
    }
}

