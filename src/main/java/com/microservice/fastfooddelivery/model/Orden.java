package com.microservice.fastfooddelivery.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;

    @Column(nullable = false)
    private LocalDateTime fechaOrden;

    @Column(nullable = false, length = 50)
    private String estado = "PENDIENTE";

    @NotNull(message = "El subtotal es obligatorio")
    @Column(nullable = false)
    private Double subtotal;

    @Column(nullable = false)
    private Double descuento = 0.0;

    @Column(nullable = false)
    private Double total;

    @NotNull(message = "El carrito es obligatorio")
    @OneToOne
    @JoinColumn(name = "id_carrito", nullable = false)
    private Carrito carrito;
}