package com.microservice.fastfooddelivery.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.CatalogoDTO;
import com.microservice.fastfooddelivery.model.Catalogo;
import com.microservice.fastfooddelivery.repository.CatalogoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CatalogoService {

    private static final Logger log = LoggerFactory.getLogger(CatalogoService.class);

    @Autowired
    private CatalogoRepository catalogoRepository;

    public List<CatalogoDTO> obtenerTodos() {
        log.info("Obteniendo todos los catalogos");
        return catalogoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public CatalogoDTO guardarCatalogoDTO(Catalogo catalogo) {
        log.info("Guardando nuevo catalogo: {}", catalogo.getNombre_catalogo());
        Catalogo guardado = catalogoRepository.save(catalogo);
        log.info("Catalogo guardado con ID: {}", guardado.getId_catalogo());
        return convertirADTO(guardado);
    }

    public CatalogoDTO actualizarCatalogoDTO(Integer id, Catalogo catalogo) {
        log.info("Actualizando catalogo con ID: {}", id);
        Catalogo catalog = catalogoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Catalogo no encontrado con ID: {}", id);
                    return new RuntimeException("El catalogo no existe en nuestros registros.");
                });
        if (catalogo.getCategoria() != null) catalog.setCategoria(catalogo.getCategoria());
        if (catalogo.getNombre_catalogo() != null) catalog.setNombre_catalogo(catalogo.getNombre_catalogo());
        if (catalogo.getDescripcion_catalogo() != null) catalog.setDescripcion_catalogo(catalogo.getDescripcion_catalogo());
        if (catalogo.getPrecio() != null) catalog.setPrecio(catalogo.getPrecio());
        if (catalogo.getDisponible() != null) catalog.setDisponible(catalogo.getDisponible());
        log.info("Catalogo con ID: {} actualizado exitosamente", id);
        return convertirADTO(catalogoRepository.save(catalog));
    }

    public CatalogoDTO buscarPorId(Integer id) {
        log.info("Buscando catalogo con ID: {}", id);
        Catalogo catalogo = catalogoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Catalogo no encontrado con ID: {}", id);
                    return new RuntimeException("Catalogo no encontrado");
                });
        return convertirADTO(catalogo);
    }

    public String eliminar(Integer id) {
        log.info("Eliminando catalogo con ID: {}", id);
        Catalogo catalogo = catalogoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Catalogo no encontrado con ID: {}", id);
                    return new RuntimeException("El catalogo con ID " + id + " no existe.");
                });
        catalogoRepository.delete(catalogo);
        log.info("Catalogo {} eliminado exitosamente", catalogo.getNombre_catalogo());
        return "El catalogo '" + catalogo.getNombre_catalogo() + "' ha sido eliminado correctamente.";
    }

    private CatalogoDTO convertirADTO(Catalogo catalogo) {
        CatalogoDTO dto = new CatalogoDTO();
        dto.setId_catalogo(catalogo.getId_catalogo());
        dto.setNombre_catalogo(catalogo.getNombre_catalogo());
        dto.setDescripcion_catalogo(catalogo.getDescripcion_catalogo());
        dto.setPrecio(catalogo.getPrecio());
        dto.setCategoria(catalogo.getCategoria());
        dto.setDisponible(catalogo.getDisponible());
        return dto;
    }
}