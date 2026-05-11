package com.microservice.fastfooddelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.fastfooddelivery.model.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    // Buscar ordenes por estado
    List<Orden> findByEstado(String estado);

    // Buscar orden por carrito
    Orden findByCarritoIdCarrito(Integer idCarrito);
}