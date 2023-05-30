package com.example.zoo.service;

import com.example.zoo.controller.resources.TicketResource;

import java.util.List;

public interface TicketService {
    List<TicketResource> getAll();

    TicketResource getById(long id);

    TicketResource save(TicketResource ticket);

    TicketResource update(TicketResource ticket, long id);

    void delete(long id);
}
