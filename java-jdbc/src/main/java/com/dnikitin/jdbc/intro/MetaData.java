package com.dnikitin.jdbc.intro;

import com.dnikitin.jdbc.util.ConnectionManager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetaData {
    public static void main(String[] args) {
        checkMetaData();
    }

    private static void checkMetaData() {
        try (Connection connection = ConnectionManager.openConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            //can get Tables, Columns, DataBases from whole server

            ResultSet catalogs = metaData.getCatalogs();
            while (catalogs.next()) {
                System.out.println(catalogs.getString(1));
//                event_master
//                flight_repository
//                flight_repository_2
//                postgres
            }
            System.out.println();


            ResultSet schemas = metaData.getSchemas();
            while (schemas.next()) {
                System.out.println(schemas.getString(1));
                //schemas only for my DB from connection
            }

            System.out.println();

            ResultSet tables = metaData.getTables(connection.getCatalog(), "public", "%", new String[]{"TABLE"});
            while (tables.next()) {
                System.out.println(tables.getString("TABLE_NAME"));
//                aircraft
//                airport
//                flight
//                seat
//                ticket
            }

            System.out.println();

            ResultSet columns = metaData.getColumns(connection.getCatalog(),
                    "public",
                    "ticket",
                    null);
            while (columns.next()) {
                System.out.println(columns.getString("COLUMN_NAME") + ": " + columns.getString("TYPE_NAME"));
//              id: bigserial
//              passenger_no: varchar
//              passenger_name: varchar
//              flight_id: int8
//              seat_no: varchar
//              cost: numeric
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
