package com.example.demo.departement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Department {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private Long id;
    private String name;

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}





