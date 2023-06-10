package com.example.zoo.service.impl;

import com.example.zoo.controller.resources.VisitorResource;
import com.example.zoo.entity.Ticket;
import com.example.zoo.entity.Visitor;
import com.example.zoo.repository.TicketRepository;
import com.example.zoo.repository.VisitorRepository;
import com.example.zoo.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.zoo.mapper.VisitorMapper.VISITOR_MAPPER;

@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {
    private final VisitorRepository visitorRepository;

    private final TicketRepository ticketRepository;

    @Override
    public List<VisitorResource> getAll() {
        return VISITOR_MAPPER.toVisitorResources(visitorRepository.findAll());
    }

    @Override
    public VisitorResource getById(long id) {
        return VISITOR_MAPPER.toVisitorResource(visitorRepository.getReferenceById(id));
    }

    @Override
    public VisitorResource save(VisitorResource resource) {
        Visitor visitor = VISITOR_MAPPER.fromVisitorResource(resource);
        visitor.setTickets(null);

        return VISITOR_MAPPER.toVisitorResource(visitorRepository.save(visitor));
    }

    @Override
    public VisitorResource update(VisitorResource resource, long id) {
        Visitor toUpdate = visitorRepository.getReferenceById(id);
        toUpdate.setName(resource.getName());
        toUpdate.setAge(resource.getAge());

        return VISITOR_MAPPER.toVisitorResource(visitorRepository.save(toUpdate));
    }

    @Override
    public void delete(long id) {
        List<Ticket> ticketsToDelete = ticketRepository.findAllByVisitorId(id);
        ticketRepository.deleteAll(ticketsToDelete);

        visitorRepository.deleteById(id);
    }

}
