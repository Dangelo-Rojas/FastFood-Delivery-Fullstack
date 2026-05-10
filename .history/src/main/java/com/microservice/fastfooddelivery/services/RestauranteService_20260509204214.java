package com.microservice.fastfooddelivery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.CatalogoDTO;
import com.microservice.fastfooddelivery.model.Restaurante;
import com.microservice.fastfooddelivery.repository.RestauranteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<CatalogoDTO> obtenerTodos() {
        return catalogoRepository.findAll().stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public Restaurante guardarRestaurante(Restaurante restaurante){
        return restauranteRepository.save(restaurante);
    }
}
