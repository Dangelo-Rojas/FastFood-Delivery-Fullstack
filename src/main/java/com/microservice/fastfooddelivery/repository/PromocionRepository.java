package com.microservice.fastfooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.fastfooddelivery.model.Promocion;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
}