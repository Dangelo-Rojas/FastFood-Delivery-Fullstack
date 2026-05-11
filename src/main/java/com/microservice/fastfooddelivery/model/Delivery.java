package com.microservice.fastfooddelivery.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelivery;

    @Column(nullable = false)
    private LocalDateTime horaSalida;

    @Column
    private LocalDateTime horaLlegada;

    @Column(nullable = false, length = 50)
    private String estado = "PENDIENTE";

    @NotNull(message = "La orden es obligatoria")
    @OneToOne
    @JoinColumn(name = "id_orden", nullable = false)
    private Orden orden;

    @NotNull(message = "El conductor es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_conductor", nullable = false)
    private Conductor conductor;
}