package com.microservice.fastfooddelivery.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.PagoDTO;
import com.microservice.fastfooddelivery.model.MetodoPago;
import com.microservice.fastfooddelivery.model.Pago;
import com.microservice.fastfooddelivery.repository.MetodoPagoRepository;
import com.microservice.fastfooddelivery.repository.PagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {

    private static final Logger log = LoggerFactory.getLogger(PagoService.class);

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public List<PagoDTO> obtenerTodos() {
        log.info("Obteniendo todos los pagos");
        return pagoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<PagoDTO> obtenerPorOrden(Long idOrden) {
        log.info("Obteniendo pagos de la orden con ID: {}", idOrden);
        return pagoRepository.findByIdOrden(idOrden).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<PagoDTO> obtenerPorEstado(String estado) {
        log.info("Obteniendo pagos con estado: {}", estado);
        return pagoRepository.findByEstado(estado).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public PagoDTO buscarPorId(Long id) {
        log.info("Buscando pago con ID: {}", id);
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Pago no encontrado con ID: {}", id);
                    return new RuntimeException("Pago con ID " + id + " no existe.");
                });
        return convertirADTO(pago);
    }

    public PagoDTO guardar(Pago pago) {
        log.info("Guardando nuevo pago para orden ID: {}", pago.getIdOrden());
        MetodoPago metodoPago = metodoPagoRepository.findById(pago.getMetodoPago().getIdMetodoPago())
                .orElseThrow(() -> {
                    log.error("Metodo de pago no encontrado");
                    return new RuntimeException("Método de pago no encontrado");
                });
        pago.setMetodoPago(metodoPago);
        // Regla de negocio: registrar fecha de pago automáticamente
        pago.setFechaPago(LocalDateTime.now());
        pago.setEstado("APROBADO");
        PagoDTO guardado = convertirADTO(pagoRepository.save(pago));
        log.info("Pago guardado con ID: {}", guardado.getIdPago());
        return guardado;
    }

    public PagoDTO cambiarEstado(Long id, String nuevoEstado) {
        log.info("Cambiando estado de pago ID: {} a {}", id, nuevoEstado);
        if (!nuevoEstado.equals("PENDIENTE") && !nuevoEstado.equals("APROBADO") &&
            !nuevoEstado.equals("RECHAZADO") && !nuevoEstado.equals("REEMBOLSADO")) {
            throw new RuntimeException("Estado inválido: " + nuevoEstado);
        }
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Pago no encontrado con ID: {}", id);
                    return new RuntimeException("Pago con ID " + id + " no existe.");
                });
        pago.setEstado(nuevoEstado);
        log.info("Estado de pago ID: {} cambiado a {}", id, nuevoEstado);
        return convertirADTO(pagoRepository.save(pago));
    }

    public String eliminar(Long id) {
        log.info("Eliminando pago con ID: {}", id);
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Pago no encontrado con ID: {}", id);
                    return new RuntimeException("Pago con ID " + id + " no existe.");
                });
        pagoRepository.delete(pago);
        log.info("Pago con ID: {} eliminado exitosamente", id);
        return "Pago eliminado exitosamente.";
    }

    private PagoDTO convertirADTO(Pago pago) {
        PagoDTO dto = new PagoDTO();
        dto.setIdPago(pago.getId());
        dto.setIdOrden(pago.getIdOrden());
        dto.setMonto(pago.getMonto());
        dto.setEstado(pago.getEstado());
        dto.setFechaPago(pago.getFechaPago());
        if (pago.getMetodoPago() != null) {
            dto.setNombreMetodoPago(pago.getMetodoPago().getNombre());
        }
        return dto;
    }
}