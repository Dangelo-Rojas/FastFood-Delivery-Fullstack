package com.microservice.fastfooddelivery.DTO;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}
