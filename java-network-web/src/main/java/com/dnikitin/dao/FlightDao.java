package com.dnikitin.dao;

import com.dnikitin.entity.FlightEntity;
import com.dnikitin.entity.FlightStatus;
import com.dnikitin.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Integer, FlightEntity> {

    private static final String FIND_ALL_SQL = """
            select id, 
                   flight_no, 
                   departure_date, 
                   departure_airport_code, 
                   arrival_date, 
                   arrival_airport_code, 
                   aircraft_id, status
            from flight
            """;

    private static final FlightDao INSTANCE = new FlightDao();

    private FlightDao() {}

    public static FlightDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<FlightEntity> findAll() {
        try (Connection connection = ConnectionManager.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<FlightEntity> flights = new ArrayList<>();
            while (resultSet.next()) {
                FlightEntity flightEntity = buildFlightEntity(resultSet);
                flights.add(flightEntity);
            }
            return flights;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private FlightEntity buildFlightEntity(ResultSet resultSet) {
        FlightEntity flight = null;
        try {
            flight = FlightEntity.builder()
                    .flightNo(resultSet.getString("flight_no"))
                    .departureDate(resultSet.getTimestamp("departure_date").toLocalDateTime())
                    .departureAirportCode(resultSet.getString("departure_airport_code"))
                    .arrivalDate(resultSet.getTimestamp("arrival_date").toLocalDateTime())
                    .arrivalAirportCode(resultSet.getString("arrival_airport_code"))
                    .aircraftId(resultSet.getInt("aircraft_id"))
                    .status(FlightStatus.fromLabel(resultSet.getString("status")))
                    .build();
            flight.setId(resultSet.getLong("id"));
            return flight;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<FlightEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(FlightEntity entity) {

    }

    @Override
    public FlightEntity save(FlightEntity entity) {
        return null;
    }
}
