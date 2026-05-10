package com.microservice.fastfooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservice.fastfooddelivery.model.MetodoPago;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long>{

}
