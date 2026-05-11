package com.microservice.fastfooddelivery.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.ConductorDTO;
import com.microservice.fastfooddelivery.model.Conductor;
import com.microservice.fastfooddelivery.repository.ConductorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ConductorService {

    private static final Logger log = LoggerFactory.getLogger(ConductorService.class);

    @Autowired
    private ConductorRepository conductorRepository;

    public List<ConductorDTO> obtenerTodos() {
        log.info("Obteniendo todos los conductores");
        return conductorRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<ConductorDTO> obtenerDisponibles() {
        log.info("Obteniendo conductores disponibles");
        return conductorRepository.findByDisponible(true).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ConductorDTO buscarPorId(Long id) {
        log.info("Buscando conductor con ID: {}", id);
        Conductor conductor = conductorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Conductor no encontrado con ID: {}", id);
                    return new RuntimeException("Conductor con ID " + id + " no encontrado.");
                });
        return convertirADTO(conductor);
    }

    public ConductorDTO guardar(Conductor conductor) {
        log.info("Guardando nuevo conductor: {} {}", conductor.getNombre(), conductor.getApellido());
        ConductorDTO guardado = convertirADTO(conductorRepository.save(conductor));
        log.info("Conductor guardado con ID: {}", guardado.getIdConductor());
        return guardado;
    }

    public ConductorDTO actualizar(Long id, Conductor datos) {
        log.info("Actualizando conductor con ID: {}", id);
        Conductor conductor = conductorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Conductor no encontrado con ID: {}", id);
                    return new RuntimeException("Conductor con ID " + id + " no encontrado.");
                });
        conductor.setNombre(datos.getNombre());
        conductor.setApellido(datos.getApellido());
        conductor.setTelefono(datos.getTelefono());
        conductor.setPatenteVehiculo(datos.getPatenteVehiculo());
        log.info("Conductor con ID: {} actualizado exitosamente", id);
        return convertirADTO(conductorRepository.save(conductor));
    }

    public ConductorDTO cambiarDisponibilidad(Long id, Boolean disponible) {
        log.info("Cambiando disponibilidad del conductor con ID: {} a {}", id, disponible);
        Conductor conductor = conductorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Conductor no encontrado con ID: {}", id);
                    return new RuntimeException("Conductor con ID " + id + " no encontrado.");
                });
        conductor.setDisponible(disponible);
        log.info("Disponibilidad del conductor ID: {} cambiada a {}", id, disponible);
        return convertirADTO(conductorRepository.save(conductor));
    }

    public String eliminar(Long id) {
        log.info("Eliminando conductor con ID: {}", id);
        Conductor conductor = conductorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Conductor no encontrado con ID: {}", id);
                    return new RuntimeException("Conductor con ID " + id + " no encontrado.");
                });
        conductorRepository.delete(conductor);
        log.info("Conductor {} {} eliminado exitosamente", conductor.getNombre(), conductor.getApellido());
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