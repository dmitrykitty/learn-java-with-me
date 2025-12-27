package com.dnikitin.jdbc.dao.ticketdao;

import com.dnikitin.jdbc.entity.TicketEntity;
import com.dnikitin.jdbc.exception.DaoException;
import com.dnikitin.jdbc.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DAO Interface: Defines the standard operations (CRUD) to be performed
 * on a model object (e.g., FlightDao.java).
 */
public interface TicketDao {

    boolean delete(Long id);

    TicketEntity save(TicketEntity ticket);

}
