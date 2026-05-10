package com.microservice.fastfooddelivery.service;

import java.util.List;

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
    
    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private RegionRepository regionRepository;

    public List<ComunaDTO> obtenerTodas() {
        return comunaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ComunaDTO buscarPorId(Long id) {
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La comuna con ID " + id + " No encontrada"));
        return convertirADTO(comuna);
    }

    public ComunaDTO guardar(Comuna comuna) {
        // Buscar la región completa antes de guardar
        Region region = regionRepository.findById(comuna.getRegion().getIdRegion())
                .orElseThrow(() -> new RuntimeException("Región no encontrada"));
        comuna.setRegion(region);
        Comuna guardada = comunaRepository.save(comuna);
        return convertirADTO(guardada);
    }

    public Comuna actualizar(Long id, Comuna datosComuna) {
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La comuna con ID " + id + " No encontrada"));
        comuna.setNombre(datosComuna.getNombre());
        return comunaRepository.save(comuna);
    }

    public String eliminar(Long id) {
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La comuna con ID " + id + " No encontrada"));
        comunaRepository.delete(comuna);
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
