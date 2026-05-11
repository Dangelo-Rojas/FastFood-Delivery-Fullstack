package com.microservice.fastfooddelivery.DTO;

import lombok.Data;

@Data
public class CarritoItemDTO {

    private Integer idCarritoItem;
    private Integer cantidad;
    private Double precioUnitario;
    private Integer idCarrito;
}