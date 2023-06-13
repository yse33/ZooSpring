package com.example.zoo.controller.resources;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VisitorResource {
    private Long id;

    private String name;

    private int age;

    private List<TicketResource> tickets;

    private Date createdDate;
}
