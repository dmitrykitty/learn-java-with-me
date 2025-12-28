package com.dnikitin.jdbc.dao;

/**
 * DATA ACCESS OBJECT (DAO) PATTERN OVERVIEW
 * -----------------------------------------
 * * 1. DEFINITION:
 * The DAO pattern is a structural pattern that provides an abstract interface
 * to some type of database or other persistence mechanism.
 * * 2. PRIMARY GOAL:
 * To separate the "Business Logic" (how the app works) from the
 * "Persistence Logic" (how data is stored and retrieved).
 * * 3. CORE COMPONENTS:
 * - Model/Entity Class: A simple POJO (Plain Old Java Object) representing
 * a database row (e.g., Flight.java, Ticket.java).
 * - DAO Interface: Defines the standard operations (CRUD) to be performed
 * on a model object (e.g., FlightDao.java).
 * - DAO Implementation: The concrete class that contains JDBC code,
 * SQL queries, and handles ResultSet mapping (e.g., FlightDaoImpl.java).
 * * 4. KEY BENEFITS:
 * - Encapsulation: Hides SQL complexity (PreparedStatement, ResultSet)
 * from the rest of the application.
 * - Maintainability: If you change a column name in SQL, you only
 * update the DAO implementation, not the entire business service.
 * - Testability: Allows developers to "mock" the database layer
 * during unit testing without needing a real database connection.
 */

// Example logic flow:
// Business Service -> DAO Interface -> Database
public class DaoTheory {

}
