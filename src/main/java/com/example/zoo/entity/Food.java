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
public class Food {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int amount;

    @OneToMany(mappedBy = "food")
    @NotAudited
    private List<Animal> animals;

    @CreatedDate
    private Date createdDate;

    @PrePersist
    @PreUpdate
    @PreRemove
    public void callAfterAction() {
        createdDate = new Date();
    }

}
