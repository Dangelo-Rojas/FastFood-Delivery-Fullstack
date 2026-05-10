package com.microservice.fastfooddelivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.MetodoPagoDTO;
import com.microservice.fastfooddelivery.model.MetodoPago;
import com.microservice.fastfooddelivery.repository.MetodoPagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public List<MetodoPagoDTO> obtenerTodos() {
        return metodoPagoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public MetodoPagoDTO buscarPorId(Long id) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago con el ID " + id + " no encontrado" ));
        return convertirADTO(metodoPago);
    }

    public MetodoPagoDTO guardar(MetodoPago metodoPago) {
        return convertirADTO(metodoPagoRepository.save(metodoPago));
    }

    public MetodoPagoDTO actualizar(Long id, MetodoPago datos) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago con el ID " + id + " no encontrado"));
        metodoPago.setNombre(datos.getNombre());
        return convertirADTO(metodoPagoRepository.save(metodoPago));
    }

    public String eliminar(Long id) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("\"Método de pago con el ID " + id + " no encontrado\""));
        metodoPagoRepository.delete(metodoPago);
        return "Método de pago " + metodoPago.getNombre() + " eliminado exitosamente.";
    }

    private MetodoPagoDTO convertirADTO(MetodoPago metodoPago) {
        MetodoPagoDTO dto = new MetodoPagoDTO();
        dto.setIdMetodoPago(metodoPago.getIdMetodoPago());
        dto.setNombre(metodoPago.getNombre());
        return dto;
    }
    
}
