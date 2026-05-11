package com.microservice.fastfooddelivery.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.DeliveryDTO;
import com.microservice.fastfooddelivery.model.Conductor;
import com.microservice.fastfooddelivery.model.Delivery;
import com.microservice.fastfooddelivery.model.Orden;
import com.microservice.fastfooddelivery.repository.ConductorRepository;
import com.microservice.fastfooddelivery.repository.DeliveryRepository;
import com.microservice.fastfooddelivery.repository.OrdenRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeliveryService {

    private static final Logger log = LoggerFactory.getLogger(DeliveryService.class);

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    public List<DeliveryDTO> obtenerTodos() {
        log.info("Obteniendo todos los deliveries");
        return deliveryRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<DeliveryDTO> obtenerPorEstado(String estado) {
        log.info("Obteniendo deliveries con estado: {}", estado);
        return deliveryRepository.findByEstado(estado).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<DeliveryDTO> obtenerPorConductor(Long idConductor) {
        log.info("Obteniendo deliveries del conductor ID: {}", idConductor);
        return deliveryRepository.findByConductorIdConductor(idConductor).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public DeliveryDTO buscarPorId(Long id) {
        log.info("Buscando delivery con ID: {}", id);
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Delivery no encontrado con ID: {}", id);
                    return new RuntimeException("Delivery no encontrado con ID: " + id);
                });
        return convertirADTO(delivery);
    }

    public DeliveryDTO buscarPorOrden(Long idOrden) {
        log.info("Buscando delivery de la orden ID: {}", idOrden);
        Delivery delivery = deliveryRepository.findByOrdenIdOrden(idOrden);
        if (delivery == null) {
            log.error("No existe delivery para la orden con ID: {}", idOrden);
            throw new RuntimeException("No existe delivery para la orden con ID: " + idOrden);
        }
        return convertirADTO(delivery);
    }

    public DeliveryDTO guardar(Delivery delivery) {
        log.info("Creando nuevo delivery para orden ID: {}", delivery.getOrden().getIdOrden());
        Orden orden = ordenRepository.findById(delivery.getOrden().getIdOrden())
                .orElseThrow(() -> {
                    log.error("Orden no encontrada con ID: {}", delivery.getOrden().getIdOrden());
                    return new RuntimeException("Orden no encontrada con ID: " + delivery.getOrden().getIdOrden());
                });
        Conductor conductor = conductorRepository.findById(delivery.getConductor().getIdConductor())
                .orElseThrow(() -> {
                    log.error("Conductor no encontrado con ID: {}", delivery.getConductor().getIdConductor());
                    return new RuntimeException("Conductor no encontrado con ID: " + delivery.getConductor().getIdConductor());
                });
        delivery.setOrden(orden);
        delivery.setConductor(conductor);
        // Regla de negocio: registrar hora de salida automáticamente
        delivery.setHoraSalida(LocalDateTime.now());
        DeliveryDTO guardado = convertirADTO(deliveryRepository.save(delivery));
        log.info("Delivery creado con ID: {}", guardado.getIdDelivery());
        return guardado;
    }

    public DeliveryDTO cambiarEstado(Long id, String nuevoEstado) {
        log.info("Cambiando estado de delivery ID: {} a {}", id, nuevoEstado);
        if (!nuevoEstado.equals("PENDIENTE") && !nuevoEstado.equals("EN_CAMINO") &&
            !nuevoEstado.equals("ENTREGADO") && !nuevoEstado.equals("CANCELADO")) {
            throw new RuntimeException("Estado inválido: " + nuevoEstado);
        }
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Delivery no encontrado con ID: {}", id);
                    return new RuntimeException("Delivery no encontrado con ID: " + id);
                });
        // Regla de negocio: registrar hora de llegada cuando se entrega
        if (nuevoEstado.equals("ENTREGADO")) {
            delivery.setHoraLlegada(LocalDateTime.now());
        }
        delivery.setEstado(nuevoEstado);
        log.info("Estado de delivery ID: {} cambiado a {}", id, nuevoEstado);
        return convertirADTO(deliveryRepository.save(delivery));
    }

    public String eliminar(Long id) {
        log.info("Eliminando delivery con ID: {}", id);
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Delivery no encontrado con ID: {}", id);
                    return new RuntimeException("Delivery no encontrado con ID: " + id);
                });
        deliveryRepository.delete(delivery);
        log.info("Delivery con ID: {} eliminado exitosamente", id);
        return "Delivery con ID " + id + " eliminado exitosamente.";
    }

    private DeliveryDTO convertirADTO(Delivery delivery) {
        DeliveryDTO dto = new DeliveryDTO();
        dto.setIdDelivery(delivery.getIdDelivery());
        dto.setHoraSalida(delivery.getHoraSalida());
        dto.setHoraLlegada(delivery.getHoraLlegada());
        dto.setEstado(delivery.getEstado());
        dto.setIdOrden(delivery.getOrden().getIdOrden());
        dto.setIdConductor(delivery.getConductor().getIdConductor());
        dto.setNombreConductor(delivery.getConductor().getNombre());
        return dto;
    }
}