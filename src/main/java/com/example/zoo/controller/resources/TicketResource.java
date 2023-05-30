package com.example.zoo.controller.resources;

import lombok.Data;

import java.util.Date;

@Data
public class TicketResource {
    private Long id;

    private Date date;

    private int price;

    private String visitor;
}
