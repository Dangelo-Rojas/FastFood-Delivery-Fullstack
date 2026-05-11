package com.microservice.fastfooddelivery.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.MetodoPagoDTO;
import com.microservice.fastfooddelivery.model.MetodoPago;
import com.microservice.fastfooddelivery.repository.MetodoPagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoPagoService {

    private static final Logger log = LoggerFactory.getLogger(MetodoPagoService.class);

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public List<MetodoPagoDTO> obtenerTodos() {
        log.info("Obteniendo todos los metodos de pago");
        return metodoPagoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public MetodoPagoDTO buscarPorId(Long id) {
        log.info("Buscando metodo de pago con ID: {}", id);
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Metodo de pago no encontrado con ID: {}", id);
                    return new RuntimeException("Método de pago con el ID " + id + " no encontrado");
                });
        return convertirADTO(metodoPago);
    }

    public MetodoPagoDTO guardar(MetodoPago metodoPago) {
        log.info("Guardando nuevo metodo de pago: {}", metodoPago.getNombre());
        MetodoPagoDTO guardado = convertirADTO(metodoPagoRepository.save(metodoPago));
        log.info("Metodo de pago guardado con ID: {}", guardado.getIdMetodoPago());
        return guardado;
    }

    public MetodoPagoDTO actualizar(Long id, MetodoPago datos) {
        log.info("Actualizando metodo de pago con ID: {}", id);
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Metodo de pago no encontrado con ID: {}", id);
                    return new RuntimeException("Método de pago con el ID " + id + " no encontrado");
                });
        metodoPago.setNombre(datos.getNombre());
        log.info("Metodo de pago con ID: {} actualizado exitosamente", id);
        return convertirADTO(metodoPagoRepository.save(metodoPago));
    }

    public String eliminar(Long id) {
        log.info("Eliminando metodo de pago con ID: {}", id);
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Metodo de pago no encontrado con ID: {}", id);
                    return new RuntimeException("Método de pago con el ID " + id + " no encontrado");
                });
        metodoPagoRepository.delete(metodoPago);
        log.info("Metodo de pago {} eliminado exitosamente", metodoPago.getNombre());
        return "Método de pago " + metodoPago.getNombre() + " eliminado exitosamente.";
    }

    private MetodoPagoDTO convertirADTO(MetodoPago metodoPago) {
        MetodoPagoDTO dto = new MetodoPagoDTO();
        dto.setIdMetodoPago(metodoPago.getIdMetodoPago());
        dto.setNombre(metodoPago.getNombre());
        return dto;
    }
}