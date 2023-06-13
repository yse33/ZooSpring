package com.example.zoo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Data
@Audited
public class Animal {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    private String species;

    private String gender;

    @ManyToOne
    @NotAudited
    private Cage cage;

    @ManyToOne
    @NotAudited
    private Food food;

    @CreatedDate
    private Date createdDate;

    private Date validFrom;

    @PrePersist
    @PreUpdate
    @PreRemove
    public void callAfterAction() {
        createdDate = new Date();
    }
}
