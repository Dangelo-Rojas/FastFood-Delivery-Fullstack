package com.microservice.fastfooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.fastfooddelivery.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {

}
