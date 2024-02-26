package com.example.demo.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del rol no puede estar vacío")
    private String nombre;

    // Getters y setters
}


