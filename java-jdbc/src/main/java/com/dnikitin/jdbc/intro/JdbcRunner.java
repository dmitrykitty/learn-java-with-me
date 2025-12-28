package com.dnikitin.jdbc.intro;

import com.dnikitin.jdbc.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        String sql3 = """
                insert into info(data) values
                ('test1'),
                ('test2'),
                ('test3');
                """;
        String sql4 = """
                select *
                from authors; 
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

            // ---------------[EXECUTEUPDATE]-----------------
            //var executeUpdate = statement.executeUpdate(sql3);//return amount of changes/inserted rows
            //System.out.println(executeUpdate);

            //to get index of inserted element
            String sql5 = """
                    insert into info(data) values
                    ('testautogen');
                    """;
            statement.executeUpdate(sql5, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = statement.getGeneratedKeys();
            if(keys.next()){
                System.out.println("index" + keys.getInt("id"));
            }


            // ---------------[EXECUTEQUERY]-----------------
            connection.setSchema("book_storage");
            ResultSet resultSet = statement.executeQuery(sql4);//almost like Iterator. Closed with statement
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("id")
                        + ": "
                        + resultSet.getString("first_name")
                        + " "
                        + resultSet.getString("last_name"));
            }
        }
    }
}
