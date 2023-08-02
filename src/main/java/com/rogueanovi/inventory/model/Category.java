package com.rogueanovi.inventory.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = -5026278002579616431L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}


