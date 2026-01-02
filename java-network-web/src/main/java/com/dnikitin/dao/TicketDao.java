package com.dnikitin.dao;

import com.dnikitin.dto.request.TicketFilter;
import com.dnikitin.entity.TicketEntity;
import com.dnikitin.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Long, TicketEntity> {
    private static final TicketDao INSTANCE = new TicketDao();

    private static final String FIND_ALL_SQL = """
            select id, passenger_no, passenger_name, flight_id, seat_no, cost
            from ticket
            """;


    private TicketDao() {}


    public static TicketDao getInstance() {
        return INSTANCE;
    }

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

        if(filter.passengerName() != null) {
            whereClauses.add("passenger_name like ?");
            params.add(filter.passengerName() + "%");
        }

        if(filter.flightId() != null) {
            whereClauses.add("flight_id = ?");
            params.add(filter.flightId());
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

        try (Connection connection = ConnectionManager.openConnection();
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TicketEntity> findAll() {
        return List.of();
    }

    @Override
    public Optional<TicketEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(TicketEntity entity) {

    }

    @Override
    public TicketEntity save(TicketEntity entity) {
        return null;
    }

    private TicketEntity buildTicketEntity(ResultSet resultSet) throws SQLException {
        TicketEntity ticketEntity = TicketEntity.builder()
                .passengerNo(resultSet.getString("passenger_no"))
                .passengerName(resultSet.getString("passenger_name"))
                .flightId(resultSet.getLong("flight_id"))
                .seatNo(resultSet.getString("seat_no"))
                .cost(resultSet.getBigDecimal("cost"))
                .build();
        ticketEntity.setId(resultSet.getLong("id"));
        return ticketEntity;
    }
}
