package com.dnikitin.jdbc.intro;

import com.dnikitin.jdbc.util.singleconnection.ConnectionManager;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transactions {


    static void main() {
        String DELETE_SQL = """
                delete from flight
                where id = ?
                """;

        String DELETE_TICKETS_SQL = """
                delete from ticket
                where flight_id = ?
                """;
        long flightId = 9L;

        deleteFlightById(DELETE_SQL, DELETE_TICKETS_SQL, flightId);
    }

    private static void deleteFlightById(String DELETE_SQL, String DELETE_TICKETS_SQL, long flightId) {
        try (Connection connection = ConnectionManager.openConnection()) {
            try (PreparedStatement deleteFlightStatement = connection.prepareStatement(DELETE_SQL);
                 PreparedStatement deleteTicketStatement = connection.prepareStatement(DELETE_TICKETS_SQL)) {

                deleteTicketStatement.setLong(1, flightId);
                deleteFlightStatement.setLong(1, flightId);

                //FIXME: deleteTicketStatement.executeUpdate();
                //problem - if after deleting ticket will get some error - flight won't be deleted but ticket will
                //FIXME: deleteFlightStatement.executeUpdate();
                //exception because tabke ticket referenced to flight so we need firsly delete ticket
                //solution - transaction
                connection.setAutoCommit(false);

                deleteTicketStatement.executeUpdate();
                deleteFlightStatement.executeUpdate();
                connection.commit(); //after transaction commit it

            } catch (Exception e) {
                connection.rollback();
                throw new RuntimeException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
