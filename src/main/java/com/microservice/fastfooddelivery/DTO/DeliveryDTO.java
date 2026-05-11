package com.microservice.fastfooddelivery.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class DeliveryDTO {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime horaSalida;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime horaLlegada;

    private Long idDelivery;
    private String estado;
    private Long idOrden;
    private Long idConductor;
    private String nombreConductor;
}