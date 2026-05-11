package com.microservice.fastfooddelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.fastfooddelivery.model.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    // Buscar deliveries por estado
    List<Delivery> findByEstado(String estado);

    // Buscar delivery por orden
    Delivery findByOrdenIdOrden(Long idOrden);

    // Buscar deliveries por conductor
    List<Delivery> findByConductorIdConductor(Long idConductor);
}