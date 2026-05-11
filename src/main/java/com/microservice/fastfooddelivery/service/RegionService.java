package com.microservice.fastfooddelivery.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.RegionDTO;
import com.microservice.fastfooddelivery.model.Region;
import com.microservice.fastfooddelivery.repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    private static final Logger log = LoggerFactory.getLogger(RegionService.class);

    @Autowired
    private RegionRepository regionRepository;

    public List<RegionDTO> obtenerTodas() {
        log.info("Obteniendo todas las regiones");
        return regionRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public RegionDTO buscarPorId(Long id) {
        log.info("Buscando region con ID: {}", id);
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Region no encontrada con ID: {}", id);
                    return new RuntimeException("Region con ID " + id + " no encontrada");
                });
        return convertirADTO(region);
    }

    public Region guardar(Region region) {
        log.info("Guardando nueva region: {}", region.getNombre());
        Region guardada = regionRepository.save(region);
        log.info("Region guardada con ID: {}", guardada.getIdRegion());
        return guardada;
    }

    public Region actualizar(Long id, Region datosRegion) {
        log.info("Actualizando region con ID: {}", id);
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Region no encontrada con ID: {}", id);
                    return new RuntimeException("Region con ID " + id + " no encontrada");
                });
        region.setNombre(datosRegion.getNombre());
        log.info("Region con ID: {} actualizada exitosamente", id);
        return regionRepository.save(region);
    }

    public String eliminar(Long id) {
        log.info("Eliminando region con ID: {}", id);
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Region no encontrada con ID: {}", id);
                    return new RuntimeException("Region con ID " + id + " no encontrada");
                });
        regionRepository.delete(region);
        log.info("Region {} eliminada exitosamente", region.getNombre());
        return "Region " + region.getNombre() + " se ha eliminado con exito.";
    }

    private RegionDTO convertirADTO(Region region) {
        RegionDTO dto = new RegionDTO();
        dto.setIdRegion(region.getIdRegion());
        dto.setNombre(region.getNombre());
        return dto;
    }
}