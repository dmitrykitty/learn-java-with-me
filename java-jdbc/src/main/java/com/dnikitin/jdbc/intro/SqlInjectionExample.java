package com.dnikitin.jdbc.intro;

import com.dnikitin.jdbc.util.singleconnection.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlInjectionExample {
    static void main() throws SQLException {

        String authorId = "3";
        List<Integer> booksByAuthorId = getBookByAuthorId(authorId); //[5, 6]
        System.out.println(booksByAuthorId);

        //problem - we can modify authorId string, to get all books, or even drop some tables
        authorId = "3 or 1 = 1";
        booksByAuthorId = getBookByAuthorId(authorId);
        System.out.println(booksByAuthorId); //[1, 3, 4, 5, 6, 9, 10, 11, 2]


        int intAuthorId = 3;
        booksByAuthorId = getBookByAuthorIdWithPrepareStatement(intAuthorId);
        System.out.println(booksByAuthorId);

        List<Integer> booksWithPagesFrom150To300 = getBookWherePagesBetween(150, 300);
        System.out.println(booksWithPagesFrom150To300); //[3, 11, 10, 9]
    }

    private static List<Integer> getBookByAuthorId(String authorId) throws SQLException {
        try (Connection connection = ConnectionManager.openConnection();
             Statement statement = connection.createStatement()) {

            connection.setSchema("book_storage");

            String sql = """
                    select id 
                    from books
                    where author_id = %s
                    """.formatted(authorId);

            ResultSet resultSet = statement.executeQuery(sql);
            List<Integer> bookIds = new ArrayList<>();
            while (resultSet.next()) {
                bookIds.add(resultSet.getInt("id"));
                //if value could be null
                //bookIds.add(resultSet.getObject("id", Integer.class));  NULL SAFE
            }
            return bookIds;
        }
    }

    //prepareStatement implements Statement so has all its functionality
    //has a lot of set methods to provide type of value
    private static List<Integer> getBookByAuthorIdWithPrepareStatement(int authorId) throws SQLException {
        String sql = """
                select id 
                from books
                where author_id = ? --add question to put param 
                """;
        try (Connection connection = ConnectionManager.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setSchema("book_storage");
            preparedStatement.setInt(1, authorId);
            preparedStatement.setFetchSize(50); //each resultSet will have 50 or less elements
            //it decreases amount of used memory and safe from out of memory exception

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> bookIds = new ArrayList<>();
            while (resultSet.next()) {
                bookIds.add(resultSet.getInt("id"));
            }
            return bookIds;

        }
    }

    private static List<Integer> getBookWherePagesBetween(int left, int right) throws SQLException {
        String sql = """
                select id 
                from books
                where pages_amount between ? and ?
                order by pages_amount desc
                """;
        try (Connection connection = ConnectionManager.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setSchema("book_storage");
            preparedStatement.setInt(1, left);
            preparedStatement.setInt(2, right);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> bookIds = new ArrayList<>();
            while (resultSet.next()) {
                bookIds.add(resultSet.getInt("id"));
            }
            return bookIds;
        }
    }
}
