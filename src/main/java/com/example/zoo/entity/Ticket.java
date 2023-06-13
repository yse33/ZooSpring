package com.example.zoo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.util.Date;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Data
@Audited(targetAuditMode = NOT_AUDITED)
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    private int price;

    @ManyToOne
    private Visitor visitor;

}
