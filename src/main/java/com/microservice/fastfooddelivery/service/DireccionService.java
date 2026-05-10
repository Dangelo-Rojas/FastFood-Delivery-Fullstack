package com.microservice.fastfooddelivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.DireccionDTO;
import com.microservice.fastfooddelivery.model.Comuna;
import com.microservice.fastfooddelivery.model.Direccion;
import com.microservice.fastfooddelivery.repository.ComunaRepository;
import com.microservice.fastfooddelivery.repository.DireccionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private ComunaRepository comunaRepository;

    public List<DireccionDTO> obtenerTodas() {
        return direccionRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<DireccionDTO> obtenerPorUsuario(Long idUsuario) {
        return direccionRepository.findByIdUsuario(idUsuario).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public DireccionDTO buscarPorId(Long id) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con ID: " + id));
        return convertirADTO(direccion);
    }

    public DireccionDTO guardar(Direccion direccion) {
        // Buscar la comuna completa antes de guardar
        Comuna comuna = comunaRepository.findById(direccion.getComuna().getIdComuna())
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
        direccion.setComuna(comuna);
        Direccion guardada = direccionRepository.save(direccion);
        return convertirADTO(guardada);
    }

    public Direccion actualizar(Long id, Direccion datos) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con ID: " + id));
        direccion.setCalle(datos.getCalle());
        direccion.setNumero(datos.getNumero());
        direccion.setDepto(datos.getDepto());
        direccion.setReferencia(datos.getReferencia());
        return direccionRepository.save(direccion);
    }

    public String eliminar(Long id) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con ID: " + id));
        direccionRepository.delete(direccion);
        return "Dirección eliminada exitosamente.";
    }

    private DireccionDTO convertirADTO(Direccion direccion) {
        DireccionDTO dto = new DireccionDTO();
        dto.setIdDireccion(direccion.getIdDireccion());
        dto.setCalle(direccion.getCalle());
        dto.setNumero(direccion.getNumero());
        dto.setDepto(direccion.getDepto());
        dto.setReferencia(direccion.getReferencia());
        dto.setIdUsuario(direccion.getIdUsuario());
        if (direccion.getComuna() != null) {
            dto.setNombreComuna(direccion.getComuna().getNombre());
            if (direccion.getComuna().getRegion() != null) {
                dto.setNombreRegion(direccion.getComuna().getRegion().getNombre());
            }
        }
        return dto;
    }

}
