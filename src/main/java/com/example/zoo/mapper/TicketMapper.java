package com.example.zoo.mapper;

import com.example.zoo.controller.resources.TicketResource;
import com.example.zoo.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TicketMapper {
    TicketMapper TICKET_MAPPER = Mappers.getMapper(TicketMapper.class);

    @Mapping(target = "visitor.name", source = "ticketResource.visitor")
    Ticket fromTicketResource(TicketResource ticketResource);

    @Mapping(target = "visitor", source = "ticket.visitor.name")
    TicketResource toTicketResource(Ticket ticket);

    List<TicketResource> toTicketResources(List<Ticket> tickets);
}
