package com.dnikitin.jdbc.dao;

import com.dnikitin.jdbc.dto.TicketFilter;
import com.dnikitin.jdbc.entity.TicketEntity;
import com.dnikitin.jdbc.exception.DaoException;
import com.dnikitin.jdbc.util.poolconnection.PoolManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDaoJDBC implements Dao<Long, TicketEntity, TicketFilter> {
    private static final TicketDaoJDBC INSTANCE = new TicketDaoJDBC();
    private final FlightDaoJDBC flightDaoJDBC = FlightDaoJDBC.getInstance();

    private static final String DELETE_SQL = """
            delete from ticket
            where id = ?
            """;

    private static final String SAVE_SQL = """
            insert into ticket(passenger_no, passenger_name, flight_id, seat_no, cost)
            values(?, ?, ?, ?, ?)
            """;

    private static final String UPDATE_SQL = """
            update ticket
            set passenger_no = ?, 
                passenger_name = ?,
                flight_id = ?,
                seat_no = ?,
                cost = ?
            where id = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            select id, passenger_no, passenger_name, flight_id, seat_no, cost
            from ticket
            where id = ?
            """;

    private static final String FIND_ALL_SQL = """
            select id, passenger_no,passenger_name, flight_id, seat_no, cost
            from ticket
            """;

    private TicketDaoJDBC() {
    }

    public static TicketDaoJDBC getInstance() {
        return INSTANCE;
    }

    @Override
    public List<TicketEntity> findAll(TicketFilter filter) {

        List<Object> params = new ArrayList<>();
        List<String> whereClauses = new ArrayList<>();

        if (filter.passengerName() != null) {
            whereClauses.add("passenger_name = ?");
            params.add(filter.passengerName());
        }

        if (filter.seatNo() != null) {
            whereClauses.add("seat_no like ?");
            params.add(filter.seatNo() + "%");
        }


        params.add(filter.limit());
        params.add(filter.offset());

        StringBuilder sqlBuilder = new StringBuilder(FIND_ALL_SQL);

        if (!whereClauses.isEmpty()) {
            sqlBuilder.append(" WHERE ");
            sqlBuilder.append(String.join(" AND ", whereClauses));
        }
        sqlBuilder.append(" LIMIT ? OFFSET ?");
        String sql = sqlBuilder.toString();

        try (Connection connection = PoolManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<TicketEntity> ticketEntities = new ArrayList<>();
            while (resultSet.next()) {
                ticketEntities.add(buildTicketEntity(resultSet));
            }
            return ticketEntities;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<TicketEntity> findAll() {
        try (Connection connection = PoolManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TicketEntity> tickets = new ArrayList<>();
            while (resultSet.next()) {
                tickets.add(buildTicketEntity(resultSet));
            }
            return tickets;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = PoolManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public TicketEntity save(TicketEntity ticket) {
        try (Connection connection = PoolManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, ticket.getPassengerNo());
            preparedStatement.setString(2, ticket.getPassengerName());
            preparedStatement.setLong(3, ticket.getFlight().getId());
            preparedStatement.setString(4, ticket.getSeatNo());
            preparedStatement.setBigDecimal(5, ticket.getCost());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                ticket.setId(generatedKeys.getLong("id"));
            }
            return ticket;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(TicketEntity ticket) {
        try (Connection connection = PoolManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, ticket.getPassengerNo());
            preparedStatement.setString(2, ticket.getPassengerName());
            preparedStatement.setLong(3, ticket.getFlight().getId());
            preparedStatement.setString(4, ticket.getSeatNo());
            preparedStatement.setBigDecimal(5, ticket.getCost());
            preparedStatement.setLong(6, ticket.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<TicketEntity> findById(Long id) {
        try (Connection connection = PoolManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            TicketEntity ticket = null;
            if (resultSet.next()) {
                ticket = buildTicketEntity(resultSet);
            }
            return Optional.ofNullable(ticket);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private TicketEntity buildTicketEntity(ResultSet resultSet) throws SQLException {
        TicketEntity ticketEntity = TicketEntity.builder()
                .passengerNo(resultSet.getString("passenger_no"))
                .passengerName(resultSet.getString("passenger_name"))
                .seatNo(resultSet.getString("seat_no"))
                .cost(resultSet.getBigDecimal("cost"))
                .build();
        ticketEntity.setId(resultSet.getLong("id"));
        ticketEntity.setFlight(flightDaoJDBC.findById(resultSet.getLong("flight_id")).orElse(null));
        return ticketEntity;
    }
}
