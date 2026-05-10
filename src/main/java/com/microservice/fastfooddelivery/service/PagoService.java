package com.microservice.fastfooddelivery.service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public List<PagoDTO> obtenerTodos() {
        return pagoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<PagoDTO> obtenerPorOrden(Long idOrden) {
        return pagoRepository.findByIdOrden(idOrden).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<PagoDTO> obtenerPorEstado(String estado) {
        return pagoRepository.findByEstado(estado).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public PagoDTO buscarPorId(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + id));
        return convertirADTO(pago);
    }

    public PagoDTO guardar(Pago pago) {
        // Buscar método de pago completo
        MetodoPago metodoPago = metodoPagoRepository.findById(pago.getMetodoPago().getIdMetodoPago())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        pago.setMetodoPago(metodoPago);
        // Regla de negocio: registrar fecha de pago automáticamente
        pago.setFechaPago(LocalDateTime.now());
        return convertirADTO(pagoRepository.save(pago));
    }

    // Regla de negocio: cambiar estado del pago
    public PagoDTO cambiarEstado(Long id, String nuevoEstado) {
        // Validar que el estado sea válido
        if (!nuevoEstado.equals("PENDIENTE") && !nuevoEstado.equals("APROBADO") &&
            !nuevoEstado.equals("RECHAZADO") && !nuevoEstado.equals("REEMBOLSADO")) {
            throw new RuntimeException("Estado inválido: " + nuevoEstado);
        }
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + id));
        pago.setEstado(nuevoEstado);
        return convertirADTO(pagoRepository.save(pago));
    }

    public String eliminar(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + id));
        pagoRepository.delete(pago);
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
