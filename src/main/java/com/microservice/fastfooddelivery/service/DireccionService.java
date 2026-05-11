package com.microservice.fastfooddelivery.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(DireccionService.class);

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private ComunaRepository comunaRepository;

    public List<DireccionDTO> obtenerTodas() {
        log.info("Obteniendo todas las direcciones");
        return direccionRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<DireccionDTO> obtenerPorUsuario(Long idUsuario) {
        log.info("Obteniendo direcciones del usuario con ID: {}", idUsuario);
        return direccionRepository.findByIdUsuario(idUsuario).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public DireccionDTO buscarPorId(Long id) {
        log.info("Buscando direccion con ID: {}", id);
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Direccion no encontrada con ID: {}", id);
                    return new RuntimeException("Dirección no encontrada con ID: " + id);
                });
        return convertirADTO(direccion);
    }

    public DireccionDTO guardar(Direccion direccion) {
        log.info("Guardando nueva direccion: {}", direccion.getCalle());
        Comuna comuna = comunaRepository.findById(direccion.getComuna().getIdComuna())
                .orElseThrow(() -> {
                    log.error("Comuna no encontrada");
                    return new RuntimeException("Comuna no encontrada");
                });
        direccion.setComuna(comuna);
        Direccion guardada = direccionRepository.save(direccion);
        log.info("Direccion guardada con ID: {}", guardada.getIdDireccion());
        return convertirADTO(guardada);
    }

    public Direccion actualizar(Long id, Direccion datos) {
        log.info("Actualizando direccion con ID: {}", id);
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Direccion no encontrada con ID: {}", id);
                    return new RuntimeException("Dirección no encontrada con ID: " + id);
                });
        direccion.setCalle(datos.getCalle());
        direccion.setNumero(datos.getNumero());
        direccion.setDepto(datos.getDepto());
        direccion.setReferencia(datos.getReferencia());
        log.info("Direccion con ID: {} actualizada exitosamente", id);
        return direccionRepository.save(direccion);
    }

    public String eliminar(Long id) {
        log.info("Eliminando direccion con ID: {}", id);
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Direccion no encontrada con ID: {}", id);
                    return new RuntimeException("Dirección no encontrada con ID: " + id);
                });
        direccionRepository.delete(direccion);
        log.info("Direccion con ID: {} eliminada exitosamente", id);
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