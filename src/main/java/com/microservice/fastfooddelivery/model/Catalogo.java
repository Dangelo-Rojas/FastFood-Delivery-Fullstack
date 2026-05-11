package com.microservice.fastfooddelivery.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "catalogo")
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_catalogo;

    @NotBlank(message = "El nombre del catálogo es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre_catalogo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 3, max = 100, message = "La descripción debe tener entre 3 a 100 caracteres")
    @Column(nullable = false, length = 100)
    private String descripcion_catalogo;

    @NotNull(message = "El precio es obligatorio")
    @Column(nullable = false)
    private Double precio;

    @NotBlank(message = "La categoría es obligatoria")
    @Size(min = 3, max = 100, message = "La categoría debe tener entre 3 a 100 caracteres")
    @Column(nullable = false, length = 100)
    private String categoria;

    @NotNull(message = "La disponibilidad es obligatoria")
    @Column(nullable = false)
    private Boolean disponible;

    @NotNull(message = "El restaurante es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;

    @OneToMany(mappedBy = "catalogo")
    @ToString.Exclude
    private List<Promocion> promociones;
}