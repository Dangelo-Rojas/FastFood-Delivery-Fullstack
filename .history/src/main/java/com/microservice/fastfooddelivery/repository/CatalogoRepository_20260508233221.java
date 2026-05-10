package com.microservice.fastfooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.fastfooddelivery.model.Catalogo;

public interface CatalogoRepository extends JpaRepository<Catalogo, Integer> {

}
