package com.example.zoo.service.impl;

import com.example.zoo.controller.resources.VisitorResource;
import com.example.zoo.entity.Ticket;
import com.example.zoo.entity.Visitor;
import com.example.zoo.repository.TicketRepository;
import com.example.zoo.repository.VisitorRepository;
import com.example.zoo.service.VisitorService;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.zoo.mapper.VisitorMapper.VISITOR_MAPPER;

@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {
    private final VisitorRepository visitorRepository;

    private final TicketRepository ticketRepository;

    private final EntityManagerFactory entityManagerFactory;

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

    @Override
    public List<VisitorResource> findAllAudits(long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> revisions = auditReader.getRevisions(Visitor.class, id);
        List<VisitorResource> results = new ArrayList<>();

        for (Number revision : revisions) {
            Visitor visitor = auditReader.find(Visitor.class, id, revision);
            results.add(VISITOR_MAPPER.toVisitorResource(visitor));
        }

        return results;
    }

    @Override
    public List<VisitorResource> findAllVisitorsByYear(int year) {
        List<VisitorResource> results = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (Visitor visitor : visitorRepository.findAll()) {
            for (Ticket ticket : visitor.getTickets()) {
                calendar.setTime(ticket.getDate());
                if (calendar.get(Calendar.YEAR) == year) {
                    results.add(VISITOR_MAPPER.toVisitorResource(visitor));
                    break;
                }
            }
        }

        return results;
    }

}
