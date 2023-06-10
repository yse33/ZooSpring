package com.example.zoo.service.impl;


import com.example.zoo.controller.resources.TicketResource;
import com.example.zoo.entity.Ticket;
import com.example.zoo.repository.TicketRepository;
import com.example.zoo.repository.VisitorRepository;
import com.example.zoo.service.TicketService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.zoo.mapper.TicketMapper.TICKET_MAPPER;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final VisitorRepository visitorRepository;

    @Override
    public List<TicketResource> getAll() {
        return TICKET_MAPPER.toTicketResources(ticketRepository.findAll());
    }

    @Override
    public TicketResource getById(long id) {
        return TICKET_MAPPER.toTicketResource(ticketRepository.getReferenceById(id));
    }

    @Override
    public TicketResource save(TicketResource resource) {
        Ticket ticket = TICKET_MAPPER.fromTicketResource(resource);
        visitorRepository.getVisitorByName(resource.getVisitor()).ifPresentOrElse(
                ticket::setVisitor,
                () -> {
                    throw new EntityNotFoundException("");
                }
        );

        return TICKET_MAPPER.toTicketResource(ticketRepository.save(ticket));
    }

    @Override
    public TicketResource update(TicketResource resource, long id) {
        Ticket toUpdate = ticketRepository.getReferenceById(id);
        toUpdate.setDate(resource.getDate());
        toUpdate.setPrice(resource.getPrice());

        return TICKET_MAPPER.toTicketResource(ticketRepository.save(toUpdate));
    }

    @Override
    public void delete(long id) {
        ticketRepository.deleteById(id);
    }
}
