package com.microservice.fastfooddelivery.DTO;

import lombok.Data;

@Data
public class PromocionDTO {


    private Integer id_promocion;
    private String nombre_promocion;
    private String descripcion_promocion;
    private Double precio_promocion;
}
