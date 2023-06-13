package com.example.zoo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Audited
public class Worker {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    private int salary;

    @CreatedDate
    private Date createdDate;

    @ManyToMany
    @NotAudited
    @JoinTable(
            name = "worker_cage",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "cage_id")
    )
    private List<Cage> cages;

    @PrePersist
    @PreUpdate
    @PreRemove
    public void callAfterAction() {
        createdDate = new Date();
    }
}
