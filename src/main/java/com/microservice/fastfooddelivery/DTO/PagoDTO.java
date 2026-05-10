package com.microservice.fastfooddelivery.DTO;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PagoDTO {
    private Long idPago;
    private Long idOrden;
    private String nombreMetodoPago;
    private Integer monto;
    private String estado;
    private LocalDateTime fechaPago;
}
