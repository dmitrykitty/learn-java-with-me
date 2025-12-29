package com.dnikitin.jdbc.intro;

import com.dnikitin.jdbc.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchRequest {
    static void main() {

        int flightId = 8;
        String DELETE_FLIGHT_SQL = "delete from flight where id = " + flightId;
        String DELETE_TICKET_SQL = "delete from ticket where flight_id = " + flightId;

        //each request to DB sent to the DB server by TCP/IP and it takes times
        //so to reduce time and not send each query separatly - use batch request to send them all in one
        //usually used for DDL operations and delete/update/insert
        try (Connection connection = ConnectionManager.openConnection()) {
            try (Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);

                statement.addBatch(DELETE_TICKET_SQL);
                statement.addBatch(DELETE_FLIGHT_SQL);

                //result array
                //each int - amount of deleted/updated/inserted rows for each query
                int[] executeBatchResult = statement.executeBatch();
                for (int j : executeBatchResult) {
                    System.out.println(j);
                }

            } catch (Exception e){
                connection.rollback();
                throw new RuntimeException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
