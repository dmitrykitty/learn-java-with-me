package com.dnikitin.jdbc.dao.ticketdao;

import com.dnikitin.jdbc.entity.TicketEntity;
import com.dnikitin.jdbc.exception.DaoException;
import com.dnikitin.jdbc.util.ConnectionManager;

import java.sql.*;

public class TicketDaoJDBC implements TicketDao {
    private static final TicketDaoJDBC INSTANCE = new TicketDaoJDBC();

    private static final String DELETE_SQL = """
                    delete from ticket
                    where id = ?;
                    """;

    private static final String SAVE_SQL = """
                    insert into ticket(passenger_no, passenger_name, flight_id, seat_no, cost)
                    values(?, ?, ?, ?, ?);
                    """;

    private TicketDaoJDBC() {
    }

    public static TicketDaoJDBC getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public TicketEntity save(TicketEntity ticket) {
        try (Connection connection = ConnectionManager.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, ticket.getPassengerNo());
            preparedStatement.setString(2, ticket.getPassengerName());
            preparedStatement.setLong(3, ticket.getFlightId());
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


}
