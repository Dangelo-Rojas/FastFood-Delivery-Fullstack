package com.microservice.fastfooddelivery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.RestauranteDTO;
import com.microservice.fastfooddelivery.model.Promocion;
import com.microservice.fastfooddelivery.repository.PromocionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PromocionService {

    @Autowired
    private PromocionRepository promocionRepository;

    public List<PromocionDTO> obtenerTodos() {
        return promocionRepository.findAll().stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public Promocion guardarPromocion(Promocion promocion){
        return promocionRepository.save(promocion);
    }
}
