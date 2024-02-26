package com.example.demo.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String nombreProducto;

    private double precio;

    private int stock;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Getters y setters
}

