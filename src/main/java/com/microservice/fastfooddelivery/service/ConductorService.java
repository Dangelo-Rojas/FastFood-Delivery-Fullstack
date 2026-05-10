package com.microservice.fastfooddelivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.ConductorDTO;
import com.microservice.fastfooddelivery.model.Conductor;
import com.microservice.fastfooddelivery.repository.ConductorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ConductorService {
    
    @Autowired
    private ConductorRepository conductorRepository;

    public List<ConductorDTO> obtenerTodos() {
        return conductorRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<ConductorDTO> obtenerDisponibles() {
        return conductorRepository.findByDisponible(true).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ConductorDTO buscarPorId(Long id) {
        Conductor conductor = conductorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor con ID " + id + " no encontrado."));
        return convertirADTO(conductor);
    }

    public ConductorDTO guardar(Conductor conductor) {
        return convertirADTO(conductorRepository.save(conductor));
    }

    public ConductorDTO actualizar(Long id, Conductor datos) {
        Conductor conductor = conductorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor con ID " + id + " no encontrado."));
        conductor.setNombre(datos.getNombre());
        conductor.setApellido(datos.getApellido());
        conductor.setTelefono(datos.getTelefono());
        conductor.setPatenteVehiculo(datos.getPatenteVehiculo());
        return convertirADTO(conductorRepository.save(conductor));
    }

    // Regla de negocio: cambiar disponibilidad del conductor
    public ConductorDTO cambiarDisponibilidad(Long id, Boolean disponible) {
        Conductor conductor = conductorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor con ID " + id + " no encontrado."));
        conductor.setDisponible(disponible);
        return convertirADTO(conductorRepository.save(conductor));
    }

    public String eliminar(Long id) {
        Conductor conductor = conductorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor con ID " + id + " no encontrado."));
        conductorRepository.delete(conductor);
        return "Conductor " + conductor.getNombre() + " eliminado exitosamente.";
    }

    private ConductorDTO convertirADTO(Conductor conductor) {
        ConductorDTO dto = new ConductorDTO();
        dto.setIdConductor(conductor.getIdConductor());
        dto.setNombre(conductor.getNombre());
        dto.setApellido(conductor.getApellido());
        dto.setTelefono(conductor.getTelefono());
        dto.setPatenteVehiculo(conductor.getPatenteVehiculo());
        dto.setDisponible(conductor.getDisponible());
        return dto;
    }

}
