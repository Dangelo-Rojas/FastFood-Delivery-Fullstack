package com.microservice.fastfooddelivery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.PromocionDTO;
import com.microservice.fastfooddelivery.model.Promocion;
import com.microservice.fastfooddelivery.model.Restaurante;
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

    public PromocionDTO buscarPorId(Integer id) {
        Promocion promocion = promocionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Promocion no encontrada"));
        return convertirADTO(promocion);
    }

    public String eliminar(Integer id) {
        try {
            Promocion promocion = promocionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("La promocion con ID" + id + " no existe."));
            promocionRepository.delete(promocion);
            return "La promocion '" + promocion.getNombre_promocion() + "' ha sido eliminado correctamente.";
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }

    public Promocion actualizarPromocion(Integer id,Promocion promocion){
        Promocion promoci = promocionRepository.findById(id).orElseThrow(() -> new RuntimeException("La promocion no existe en nuestros registros."));
        if(restaurante.getId_restaurante() != null){
            restaurant.setId_restaurante(restaurante.getId_restaurante());
        }
        if(restaurante.getNombre_restaurante() != null){
            restaurant.setNombre_restaurante(restaurante.getNombre_restaurante());
        }
        if(restaurante.getRazon_social() != null){
            restaurant.setRazon_social(restaurante.getRazon_social());
        }
        if(restaurante.getDireccion_restaurante() != null){
            restaurant.setDireccion_restaurante(restaurante.getDireccion_restaurante());
        }
        if(restaurante.getTelefono_restaurante() != null){
            restaurant.setTelefono_restaurante(restaurante.getTelefono_restaurante());
        }
        if(restaurante.getCorreo_restaurante() != null){
            restaurant.setCorreo_restaurante(restaurante.getCorreo_restaurante());
        }
        return restauranteRepository.save(restaurant);
    }

    private PromocionDTO convertirADTO(Promocion promocion) {
        PromocionDTO dto = new PromocionDTO();
        dto.setId_promocion(promocion.getId_promocion());
        dto.setNombre_promocion(promocion.getNombre_promocion());
        dto.setDescripcion_promocion(promocion.getDescripcion_promocion());
        dto.setPrecio_promocion(promocion.getPrecio_promocion());
        return dto;
    }
}
