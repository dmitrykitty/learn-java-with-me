package com.dnikitin.jdbc.dao;

import com.dnikitin.jdbc.dto.TicketFilter;
import com.dnikitin.jdbc.entity.TicketEntity;

import java.math.BigDecimal;
import java.util.Optional;

public class DaoRunner {
    public static void main(String[] args) {
        findAllWithConditionTest();
    }

    private static void findAllWithConditionTest() {
        TicketFilter ticketFilter = new TicketFilter(10, 0, null, "C");
        Dao<Long, TicketEntity, TicketFilter> ticketDao = TicketDaoJDBC.getInstance();
        ticketDao.findAll(ticketFilter).forEach(System.out::println);
    }

    private static void findAllTest() {
        Dao<Long, TicketEntity, TicketFilter> ticketDao = TicketDaoJDBC.getInstance();
        ticketDao.findAll().forEach(System.out::println);
    }

    private static void findByIdAndUpdateTest() {
        Dao<Long, TicketEntity, TicketFilter> ticketDao = TicketDaoJDBC.getInstance();
        Optional<TicketEntity> ticketById = ticketDao.findById(2L);
        System.out.println(ticketById);

        ticketById.ifPresent(ticket -> {
            ticket.setCost(BigDecimal.valueOf(192.50));
            boolean updated = ticketDao.update(ticket);
            if (updated) {
                System.out.println("Ticket with " + ticket.getId() + " was updated");
            }
        });
    }

    private static void deleteTest() {
        TicketDaoJDBC ticketDao = TicketDaoJDBC.getInstance();
        long idToDelete = 56L;
        boolean deleted = ticketDao.delete(idToDelete);
        if (deleted) {
            System.out.println("Ticket with index " + idToDelete + " deleted.");
        } else {
            System.out.println("No ticket with index " + idToDelete + " exists.");
        }
    }

    private static void saveTest() {
        Dao<Long, TicketEntity, TicketFilter> ticketDao = TicketDaoJDBC.getInstance();

        TicketEntity ticket = TicketEntity.builder()
                .passengerNo("1234")
                .passengerName("TestPassenger")
                .seatNo("12A")
                .cost(BigDecimal.valueOf(50.50))
                .build();
        ticket.setFlight(FlightDaoJDBC.getInstance().findById(3L).orElse(null));

        TicketEntity ticketEntity = ticketDao.save(ticket);
        System.out.println(ticketEntity);
    }
}
