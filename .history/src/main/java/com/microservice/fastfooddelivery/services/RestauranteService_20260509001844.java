package com.microservice.fastfooddelivery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.repository.RestauranteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante guardarRestaurante(Restaurante restaurante){
        return restauranteRepository.save(restaurante);
    }
}
