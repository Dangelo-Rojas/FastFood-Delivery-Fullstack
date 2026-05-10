package com.microservice.fastfooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.fastfooddelivery.model.Promocion;

public interface PromocionRepository extends JpaRepository<Promocion, Integer> {

}
