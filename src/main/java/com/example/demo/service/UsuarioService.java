package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Mono<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Flux<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Mono<Usuario> crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Mono<Usuario> actualizarUsuario(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id)
                .flatMap(usuarioExistente -> {
                    // Actualizar campos según sea necesario
                    usuarioExistente.setNombreUsuario(usuarioActualizado.getNombreUsuario());
                    usuarioExistente.setApellidoUsuario(usuarioActualizado.getApellidoUsuario());
                    usuarioExistente.setEdad(usuarioActualizado.getEdad());
                    usuarioExistente.setDocumentoIdentidad(usuarioActualizado.getDocumentoIdentidad());

                    // Si la relación con Rol debe ser actualizada, setea el ID del nuevo rol
                    usuarioExistente.setRolId(usuarioActualizado.getRolId()); // Asumiendo que tienes un método getRolId()

                    usuarioExistente.setNickname(usuarioActualizado.getNickname());
                    usuarioExistente.setContraseña(usuarioActualizado.getContraseña());
                    usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
                    // Otros campos según sea necesario

                    return usuarioRepository.save(usuarioExistente);
                });
    }

    public Mono<Void> eliminarUsuario(Long id) {
        return usuarioRepository.deleteById(id);
    }
}
