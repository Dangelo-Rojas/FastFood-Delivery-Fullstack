package com.microservice.fastfooddelivery.DTO;

import lombok.Data;

@Data
public class CatalogoDTO {
    private Integer id_catalogo;
    private String nombre_catalogo;
    private String descripcion_catalogo;
    private double precio;
    private String categoria;
    private boolean disponible;
}
