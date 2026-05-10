package com.microservice.fastfooddelivery.DTO;

import lombok.Data;

@Data
public class DireccionDTO {
    private Long idDireccion;
    private String calle;
    private String numero;
    private String depto;
    private String referencia;
    private String nombreComuna;
    private String nombreRegion;
    private Long idUsuario;
}
