package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
public interface UsuarioRepository extends ReactiveCrudRepository<Usuario, Long> {

}
