package com.microservice.fastfooddelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.fastfooddelivery.model.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    // buscar todas las direcciones de un usuario
    List<Direccion> findByIdUsuario(Long idUsuario);

}
