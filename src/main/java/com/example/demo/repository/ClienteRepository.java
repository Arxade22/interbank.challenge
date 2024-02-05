package com.example.demo.repository;

import com.example.demo.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    Cliente findByNombre(@Param("nombre") String nombre);

    @Query("SELECT c FROM Cliente c WHERE c.apellidoPaterno LIKE %:apellido% OR c.apellidoMaterno LIKE %:apellido%")
    Iterable<Cliente> findByApellidoContaining(@Param("apellido") String apellido);
}

