package com.dnikitin.jdbc.dao.ticketdao;

import com.dnikitin.jdbc.entity.TicketEntity;
import com.dnikitin.jdbc.exception.DaoException;
import com.dnikitin.jdbc.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * DAO Interface: Defines the standard operations (CRUD) to be performed
 * on a model object (e.g., FlightDao.java).
 */
public interface TicketDao {

    boolean delete(Long id);

    TicketEntity save(TicketEntity ticket);

    boolean update(TicketEntity ticket);

    Optional<TicketEntity> findById(Long id);

    List<TicketEntity> findAll();

}
