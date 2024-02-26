package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;

@Table("usuarios")
public class Usuario {

    @Id
    private Long id;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String nombreUsuario;

    @NotBlank(message = "El apellido de usuario no puede estar vacío")
    private String apellidoUsuario;

    private int edad;

    @NotBlank(message = "El documento de identidad no puede estar vacío")
    private String documentoIdentidad;

    @Column("rol_id")
    private Long rolId;  // Puedes almacenar solo el ID del rol

    @NotBlank(message = "El nickname no puede estar vacío")
    private String nickname;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String contraseña;

    @NotBlank(message = "El correo no puede estar vacío")
    private String correo;

    @Column("usuario_creacion_id")
    private Long usuarioCreacionId;  // Puedes almacenar solo el ID del usuario creador

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getUsuarioCreacionId() {
        return usuarioCreacionId;
    }

    public void setUsuarioCreacionId(Long usuarioCreacionId) {
        this.usuarioCreacionId = usuarioCreacionId;
    }
}
