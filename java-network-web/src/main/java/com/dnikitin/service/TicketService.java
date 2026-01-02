package com.dnikitin.service;

import com.dnikitin.dao.TicketDao;
import com.dnikitin.dto.request.Filter;
import com.dnikitin.dto.request.TicketFilter;
import com.dnikitin.dto.response.TicketDto;

import java.util.List;

public class TicketService {
    private static final TicketDao ticketDao = TicketDao.getInstance();

    private static final TicketService INSTANCE = new TicketService();
    private TicketService() {}

    public static TicketService getInstance() {
        return INSTANCE;
    }

    public List<TicketDto> findAll(TicketFilter filter) {
        return ticketDao.findAll(filter).stream()
                .map(ticketEntity -> new TicketDto(
                        ticketEntity.getId(),
                        ticketEntity.getFlightId(),
                        ticketEntity.getSeatNo()
                ))
                .toList();
    }
}
