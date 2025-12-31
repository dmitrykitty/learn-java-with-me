package com.dnikitin.jdbc.dao;

import com.dnikitin.jdbc.dto.Filter;
import com.dnikitin.jdbc.dto.TicketFilter;
import com.dnikitin.jdbc.entity.TicketEntity;

import java.util.List;
import java.util.Optional;

/**
 * DAO Interface: Defines the standard operations (CRUD) to be performed
 * on a model object (e.g., FlightDao.java).
 */
public interface Dao<K, E, F extends Filter> {

    boolean delete(K id);

    E save(E entity);

    boolean update(E entity);

    Optional<E> findById(K id);

    List<E> findAll();

    List<E> findAll(F filter);

}
