package com.microservice.fastfooddelivery.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promocion")
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_promocion;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre_promocion;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 3, max = 100, message = "La descripción debe tener entre 3 a 100 caracteres")
    @Column(nullable = false, length = 100)
    private String descripcion_promocion;

    @NotNull(message = "El precio de la promoción es obligatorio")
    @Column(nullable = false)
    private Double precio_promocion;

    @NotNull(message = "El catálogo es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_catalogo", nullable = false)
    private Catalogo catalogo;
}