package com.microservice.fastfooddelivery.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.ComunaDTO;
import com.microservice.fastfooddelivery.model.Comuna;
import com.microservice.fastfooddelivery.model.Region;
import com.microservice.fastfooddelivery.repository.ComunaRepository;
import com.microservice.fastfooddelivery.repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    private static final Logger log = LoggerFactory.getLogger(ComunaService.class);

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private RegionRepository regionRepository;

    public List<ComunaDTO> obtenerTodas() {
        log.info("Obteniendo todas las comunas");
        return comunaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ComunaDTO buscarPorId(Long id) {
        log.info("Buscando comuna con ID: {}", id);
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Comuna no encontrada con ID: {}", id);
                    return new RuntimeException("La comuna con ID " + id + " No encontrada");
                });
        return convertirADTO(comuna);
    }

    public ComunaDTO guardar(Comuna comuna) {
        log.info("Guardando nueva comuna: {}", comuna.getNombre());
        Region region = regionRepository.findById(comuna.getRegion().getIdRegion())
                .orElseThrow(() -> {
                    log.error("Region no encontrada");
                    return new RuntimeException("Región no encontrada");
                });
        comuna.setRegion(region);
        Comuna guardada = comunaRepository.save(comuna);
        log.info("Comuna guardada con ID: {}", guardada.getIdComuna());
        return convertirADTO(guardada);
    }

    public Comuna actualizar(Long id, Comuna datosComuna) {
        log.info("Actualizando comuna con ID: {}", id);
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Comuna no encontrada con ID: {}", id);
                    return new RuntimeException("La comuna con ID " + id + " No encontrada");
                });
        comuna.setNombre(datosComuna.getNombre());
        log.info("Comuna con ID: {} actualizada exitosamente", id);
        return comunaRepository.save(comuna);
    }

    public String eliminar(Long id) {
        log.info("Eliminando comuna con ID: {}", id);
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Comuna no encontrada con ID: {}", id);
                    return new RuntimeException("La comuna con ID " + id + " No encontrada");
                });
        comunaRepository.delete(comuna);
        log.info("Comuna {} eliminada exitosamente", comuna.getNombre());
        return "Comuna " + comuna.getNombre() + " fue eliminada exitosamente";
    }

    private ComunaDTO convertirADTO(Comuna comuna) {
        ComunaDTO dto = new ComunaDTO();
        dto.setIdComuna(comuna.getIdComuna());
        dto.setNombre(comuna.getNombre());
        if (comuna.getRegion() != null) {
            dto.setNombreRegion(comuna.getRegion().getNombre());
        }
        return dto;
    }
}