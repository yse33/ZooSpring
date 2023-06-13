package com.example.zoo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.util.List;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Data
@Audited(targetAuditMode = NOT_AUDITED)
public class Cage {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String type;

    private int capacity;

    @OneToMany(mappedBy = "cage")
    private List<Animal> animals;

    @ManyToMany(mappedBy = "cages")
    private List<Worker> workers;
}
