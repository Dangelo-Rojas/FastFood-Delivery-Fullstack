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
@Table(name = "restaurante")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_restaurante;

    @NotBlank(message = "La razon social es obligatorio")
    @Column(nullable = false, length = 100)
    private String razon_social;

    @NotBlank(message = "La direccion es obligatoria")
    @Size(min = 3, max = 100, message = "La direccion debe tener al menos 3 caracteres")
    @Column(nullable = false, length = 100)
    private String direccion_restaurante;

}
