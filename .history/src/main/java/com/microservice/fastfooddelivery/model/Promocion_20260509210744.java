package com.microservice.fastfooddelivery.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Promocion")
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_promocion;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre_promocion;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 3, max = 100, message = "La descripcion debe tener entre 3 a 100 caracteres")
    @Column(nullable = false, length = 100)
    private String descripcion_promocion;

    @NotBlank(message = "El precio de la promocion es obligatorio")
    @Column(nullable = false, length = 100)
    private Double precio_promocion;
}
