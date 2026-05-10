package com.microservice.fastfooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.fastfooddelivery.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long>{

}
