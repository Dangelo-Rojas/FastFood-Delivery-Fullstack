package com.microservice.fastfooddelivery.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.PromocionDTO;
import com.microservice.fastfooddelivery.model.Catalogo;
import com.microservice.fastfooddelivery.model.Promocion;
import com.microservice.fastfooddelivery.repository.CatalogoRepository;
import com.microservice.fastfooddelivery.repository.PromocionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PromocionService {

    private static final Logger log = LoggerFactory.getLogger(PromocionService.class);

    @Autowired
    private PromocionRepository promocionRepository;

    @Autowired
    private CatalogoRepository catalogoRepository;

    public List<PromocionDTO> obtenerTodos() {
        log.info("Obteniendo todas las promociones");
        return promocionRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public PromocionDTO buscarPorId(Integer id) {
        log.info("Buscando promocion con ID: {}", id);
        Promocion promocion = promocionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Promocion no encontrada con ID: {}", id);
                    return new RuntimeException("Promocion no encontrada");
                });
        return convertirADTO(promocion);
    }

    public Promocion guardarPromocion(Promocion promocion) {
        log.info("Guardando nueva promocion: {}", promocion.getNombre_promocion());
        Catalogo catalogo = catalogoRepository.findById(promocion.getCatalogo().getId_catalogo())
                .orElseThrow(() -> {
                    log.error("Catalogo no encontrado");
                    return new RuntimeException("Catalogo no encontrado");
                });
        promocion.setCatalogo(catalogo);
        Promocion guardada = promocionRepository.save(promocion);
        log.info("Promocion guardada con ID: {}", guardada.getId_promocion());
        return guardada;
    }

    public Promocion actualizarPromocion(Integer id, Promocion promocion) {
        log.info("Actualizando promocion con ID: {}", id);
        Promocion promoci = promocionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Promocion no encontrada con ID: {}", id);
                    return new RuntimeException("La promocion no existe en nuestros registros.");
                });
        if (promocion.getNombre_promocion() != null) promoci.setNombre_promocion(promocion.getNombre_promocion());
        if (promocion.getDescripcion_promocion() != null) promoci.setDescripcion_promocion(promocion.getDescripcion_promocion());
        if (promocion.getPrecio_promocion() != null) promoci.setPrecio_promocion(promocion.getPrecio_promocion());
        log.info("Promocion con ID: {} actualizada exitosamente", id);
        return promocionRepository.save(promoci);
    }

    public String eliminar(Integer id) {
        log.info("Eliminando promocion con ID: {}", id);
        Promocion promocion = promocionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Promocion no encontrada con ID: {}", id);
                    return new RuntimeException("La promocion con ID " + id + " no existe.");
                });
        promocionRepository.delete(promocion);
        log.info("Promocion {} eliminada exitosamente", promocion.getNombre_promocion());
        return "La promocion '" + promocion.getNombre_promocion() + "' ha sido eliminada correctamente.";
    }

    private PromocionDTO convertirADTO(Promocion promocion) {
        PromocionDTO dto = new PromocionDTO();
        dto.setId_promocion(promocion.getId_promocion());
        dto.setNombre_promocion(promocion.getNombre_promocion());
        dto.setDescripcion_promocion(promocion.getDescripcion_promocion());
        dto.setPrecio_promocion(promocion.getPrecio_promocion());
        return dto;
    }
}