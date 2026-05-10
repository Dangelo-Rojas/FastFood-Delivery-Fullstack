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
        if(promocion.getId_promocion() != null){
            promoci.setId_promocion(promocion.getId_promocion());
        }
        if(promocion.getNombre_promocion() != null){
            promoci.setNombre_promocion(promocion.getNombre_promocion());
        }
        if(promocion.getDescripcion_promocion() != null){
            promoci.setDescripcion_promocion(promocion.getDescripcion_promocion());
        }
        if(promocion.getPrecio_promocion() != null){
            promoci.setPrecio_promocion(promocion.getPrecio_promocion());
        }
        return promocionRepository.save(restaurant);
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
