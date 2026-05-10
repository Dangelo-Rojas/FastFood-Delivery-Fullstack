package com.microservice.fastfooddelivery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public RestauranteDTO buscarPorId(Integer id) {
        Restaurante restaurante = restauranteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
        return convertirADTO(restaurante);
    }

    public String eliminar(Integer id) {
        try {
            Restaurante restaurante = restauranteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("El restaurante con ID" + id + " no existe."));
            restauranteRepository.delete(restaurante);
            return "El restaurante '" + restaurante.getNombre_restaurante() + "' ha sido eliminado correctamente.";
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }

    public Restaurante actualizarRestaurante(Integer id,Restaurante restaurante){
        Restaurante restaurant = restauranteRepository.findById(id).orElseThrow(() -> new RuntimeException("El restaurante no existe en nuestros registros."));
        if(restaurante.getId_restaurante() != null){
            restaurant.setId_restaurante(restaurante.getId_restaurante());
        }
        if(restaurante.getNombre_restaurante() != null){
            restaurant.setNombre_restaurante(restaurante.getNombre_restaurante());
        }
        if(catalogo.getDescripcion_catalogo() != null){
            catalog.setDescripcion_catalogo(catalogo.getDescripcion_catalogo());
        }
        if(catalogo.getId_catalogo() != null){
            catalog.setId_catalogo(catalogo.getId_catalogo());
        }
        if(catalogo.getPrecio() != null){
            catalog.setPrecio(catalogo.getPrecio());
        }
        if(catalogo.getDisponible() != null){
            catalog.setDisponible(catalogo.getDisponible());
        }
        return restauranteRepository.save(restaurant);
    }

    private RestauranteDTO convertirADTO(Restaurante restaurante) {
        RestauranteDTO dto = new RestauranteDTO();
        dto.setId_restaurante(restaurante.getId_restaurante());
        dto.setNombre_restaurante(restaurante.getNombre_restaurante());
        dto.setDireccion_restaurante(restaurante.getDireccion_restaurante());
        dto.setTelefono_restaurante(restaurante.getTelefono_restaurante());
        dto.setCorreo_restaurante(restaurante.getCorreo_restaurante());
        return dto;
    }
}
