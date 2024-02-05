package com.example.demo.service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente crearCliente(Cliente cliente) {
        // Lógica de validación y procesamiento
        if (cliente.getNombre() == null || cliente.getApellidoPaterno() == null) {
            throw new IllegalArgumentException("Nombre y apellido paterno son obligatorios");
        }
        //Setear la fecha de creacion
        cliente.setFechaCreacion(LocalDateTime.now());

        return clienteRepository.save(cliente);
    }

    public List<ClienteDTO> obtenerTodosClientes() {
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();

        return clientes.stream()
                .map(cliente -> new ClienteDTO(cliente.getId(), concatenarNombreCompleto(cliente)))
                .collect(Collectors.toList());
    }

    private String concatenarNombreCompleto(Cliente cliente) {
        return cliente.getNombre() + " " + cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno();
    }


    public Cliente actualizarCliente(Long id, Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));

        validarCampos(cliente);

        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellidoPaterno(cliente.getApellidoPaterno());
        clienteExistente.setApellidoMaterno(cliente.getApellidoMaterno());
        clienteExistente.setFechaCreacion(LocalDateTime.now());
        clienteExistente.setActivo(cliente.isActivo());

        return clienteRepository.save(clienteExistente);
    }

    private void validarCampos(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni vacío");
        }

        if (cliente.getApellidoPaterno() == null || cliente.getApellidoPaterno().isEmpty()) {
            throw new IllegalArgumentException("El apellido paterno no puede ser nulo ni vacío");
        }

    }

}

