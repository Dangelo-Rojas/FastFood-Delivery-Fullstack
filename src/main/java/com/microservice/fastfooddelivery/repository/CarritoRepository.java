package com.microservice.fastfooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.fastfooddelivery.model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer>{

}
