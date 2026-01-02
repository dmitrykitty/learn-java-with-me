package com.dnikitin.service;

import com.dnikitin.dao.FlightDao;
import com.dnikitin.dto.response.FlightDto;

import java.util.List;

public class FlightService {
    private static final FlightService INSTANCE = new FlightService();

    private final FlightDao flightDao = FlightDao.getInstance();

    private FlightService() {}

    public List<FlightDto> findAll() {
        return flightDao.findAll().stream()
                .map(flightEntity -> new FlightDto(
                        flightEntity.getId(), "%s - %s: %s".formatted(
                                flightEntity.getDepartureAirportCode(),
                                flightEntity.getArrivalAirportCode(),
                                flightEntity.getStatus().getLabel())))
                .toList();
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }
}
