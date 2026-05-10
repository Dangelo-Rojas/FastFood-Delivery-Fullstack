package com.microservice.fastfooddelivery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.CatalogoDTO;
import com.microservice.fastfooddelivery.DTO.RestauranteDTO;
import com.microservice.fastfooddelivery.model.Catalogo;
import com.microservice.fastfooddelivery.model.Restaurante;
import com.microservice.fastfooddelivery.repository.RestauranteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<RestauranteDTO> obtenerTodos() {
        return restauranteRepository.findAll().stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public Restaurante guardarRestaurante(Restaurante restaurante){
        return restauranteRepository.save(restaurante);
    }

    private RestauranteDTO convertirADTO(Restaurante restaurante) {
        RestauranteDTO dto = new RestauranteDTO();
        dto.setId_restaurante(restaurante.getId_restaurante());
        dto.setNombre_restaurante(restaurante.getNombre_restaurante());
        dto.setDireccion_restaurante(restaurante.getDireccion_restaurante());
        dto.setTelefono_restaurante(restaurante.getTelefono_restaurante());
        dto.setCorreo_restaurante(restaurante.getCorreo_restaurante());
        dto.setDisponible(catalogo.getDisponible());
        return dto;
    }
}
