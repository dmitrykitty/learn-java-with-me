package com.dnikitin.jdbc.intro;

import com.dnikitin.jdbc.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcRunner {
    static void main() throws SQLException {
        //installing dependency postgresql.org

        String sql1 = """
                create database game; 
                """;
        String sql2 = """
                create table if not exists info(
                    id serial primary key,
                    data text not null
                )
                """;


        //Connection implements autocloseable
        try (var connection = ConnectionManager.openConnection();
             var statement = connection.createStatement()) {
            //getSomeInfro from connection
            System.out.println(connection.getSchema()); //we can use setSchema to change schema to custom one
            System.out.println(connection.getMetaData());
            System.out.println(connection.getTransactionIsolation());

            var execute = statement.execute(sql2);
            //return true if returned object is ResultSet (so only for select queries)
            //execute mainly used for DDL operations as creating or droping something
            System.out.println(execute);//false, because Create executed
        }

    }
}
