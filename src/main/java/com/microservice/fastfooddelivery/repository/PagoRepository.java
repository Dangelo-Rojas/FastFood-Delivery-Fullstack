package com.microservice.fastfooddelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.fastfooddelivery.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    // buscar pagos por orden
    List<Pago> findByIdOrden(Long idOrden);

    // buscar pago por estado
    List<Pago> findByEstado(String estado);
}
