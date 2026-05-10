package com.microservice.fastfooddelivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.RegionDTO;
import com.microservice.fastfooddelivery.model.Region;
import com.microservice.fastfooddelivery.repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {
    
    @Autowired
    private RegionRepository regionRepository;

    public List<RegionDTO> obtenerTodas() {
    return regionRepository.findAll().stream()
            .map(this::convertirADTO)
            .toList();
    }

    public RegionDTO buscarPorId(Long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region con ID " + id + " no encontrada" ));
        return convertirADTO(region);
    }

    public Region guardar(Region region) {
        return regionRepository.save(region);
    }

    public Region actualizar(Long id, Region datosRegion) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region con ID " + id + " no encontrada" ));
        region.setNombre(datosRegion.getNombre());
        return  regionRepository.save(region);
    }

    public String eliminar(Long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region con ID " + id + " no encontrada" ));
        regionRepository.delete(region);
        return "Region" + region.getNombre() + " se ha eliminado con exito.";
    }

    private RegionDTO convertirADTO(Region region) {

        RegionDTO dto = new RegionDTO();
        dto.setIdRegion(region.getIdRegion());
        dto.setNombre(region.getNombre());
        return  dto;
    }

}
