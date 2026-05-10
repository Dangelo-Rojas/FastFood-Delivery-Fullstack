package com.microservice.fastfooddelivery.services;

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
        RestauranteDTO dto = new CatalogoDTO();
        dto.setId_catalogo(catalogo.getId_catalogo());
        dto.setNombre_catalogo(catalogo.getNombre_catalogo());
        dto.setDescripcion_catalogo(catalogo.getDescripcion_catalogo());;
        dto.setPrecio(catalogo.getPrecio());
        dto.setCategoria(catalogo.getCategoria());
        dto.setDisponible(catalogo.getDisponible());
        return dto;
    }
}
