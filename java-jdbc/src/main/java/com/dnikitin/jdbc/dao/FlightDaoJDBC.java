package com.dnikitin.jdbc.dao;

import com.dnikitin.jdbc.dto.Filter;
import com.dnikitin.jdbc.dto.FlightFilter;
import com.dnikitin.jdbc.entity.FlightEntity;
import com.dnikitin.jdbc.entity.Status;
import com.dnikitin.jdbc.entity.TicketEntity;
import com.dnikitin.jdbc.exception.DaoException;
import com.dnikitin.jdbc.util.poolconnection.PoolManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FlightDaoJDBC implements Dao<Long, FlightEntity, FlightFilter>{
    private static final FlightDaoJDBC INSTANCE = new FlightDaoJDBC();

    private static final String FIND_BY_ID_SQL = """
            select id, 
                   flight_no, 
                   departure_date, 
                   departure_airport_code, 
                   arrival_date, 
                   arrival_airport_code, 
                   aircraft_id, 
                   status
            from flight
            where id = ?
            """;



    private FlightDaoJDBC() {
    }
    public static FlightDaoJDBC getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public FlightEntity save(FlightEntity entity) {
        return null;
    }

    @Override
    public boolean update(FlightEntity entity) {
        return false;
    }

    @Override
    public Optional<FlightEntity> findById(Long id) {
        try (Connection connection = PoolManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            FlightEntity flight = null;
            if (resultSet.next()) {
                flight = buildFlightEntity(resultSet);
            }
            return Optional.ofNullable(flight);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FlightEntity> findAll() {
        return List.of();
    }

    @Override
    public List<FlightEntity> findAll(FlightFilter filter) {
        return List.of();
    }

    private FlightEntity buildFlightEntity(ResultSet resultSet) throws SQLException {
        FlightEntity flight = FlightEntity.builder()
                .flightNo(resultSet.getString("flight_no"))
                .departureDate(resultSet.getTimestamp("departure_date").toLocalDateTime())
                .departureAirportCode(resultSet.getString("departure_airport_code"))
                .arrivalDate(resultSet.getTimestamp("arrival_date").toLocalDateTime())
                .arrivalAirportCode(resultSet.getString("arrival_airport_code"))
                .status(Status.fromLabel(resultSet.getString("status")))
                .build();
        flight.setId(resultSet.getLong("id"));
        return flight;
    }
}
