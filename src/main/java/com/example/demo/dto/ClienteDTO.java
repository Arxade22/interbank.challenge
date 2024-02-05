package com.example.demo.dto;

public class ClienteDTO {
    private Long id;
    private String nombreCompleto;

    public ClienteDTO(Long id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public Long getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
}
