package com.microservice.fastfooddelivery.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.CarritoDTO;
import com.microservice.fastfooddelivery.model.Carrito;
import com.microservice.fastfooddelivery.repository.CarritoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarritoService {

    private static final Logger log = LoggerFactory.getLogger(CarritoService.class);

    @Autowired
    private CarritoRepository carritoRepository;

    public List<CarritoDTO> obtenerTodos() {
        log.info("Obteniendo todos los carritos");
        return carritoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public CarritoDTO guardarCarrito(Carrito carrito) {
        log.info("Guardando nuevo carrito con estado: {}", carrito.getEstado());
        CarritoDTO guardado = convertirADTO(carritoRepository.save(carrito));
        log.info("Carrito guardado con ID: {}", guardado.getIdCarrito());
        return guardado;
    }

    public CarritoDTO buscarPorId(Integer id) {
        log.info("Buscando carrito con ID: {}", id);
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Carrito no encontrado con ID: {}", id);
                    return new RuntimeException("El carrito con ID " + id + " no existe.");
                });
        return convertirADTO(carrito);
    }

    public CarritoDTO actualizarCarrito(Integer id, Carrito carrito) {
        log.info("Actualizando carrito con ID: {}", id);
        Carrito carritoExistente = carritoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Carrito no encontrado con ID: {}", id);
                    return new RuntimeException("El carrito con ID " + id + " no existe.");
                });
        if (carrito.getEstado() != null) {
            carritoExistente.setEstado(carrito.getEstado());
        }
        log.info("Carrito con ID: {} actualizado exitosamente", id);
        return convertirADTO(carritoRepository.save(carritoExistente));
    }

    public String eliminar(Integer id) {
        log.info("Eliminando carrito con ID: {}", id);
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Carrito no encontrado con ID: {}", id);
                    return new RuntimeException("El carrito con ID " + id + " no existe.");
                });
        carritoRepository.delete(carrito);
        log.info("Carrito con ID: {} eliminado exitosamente", id);
        return "El carrito con ID " + id + " ha sido eliminado correctamente.";
    }

    private CarritoDTO convertirADTO(Carrito carrito) {
        CarritoDTO dto = new CarritoDTO();
        dto.setIdCarrito(carrito.getIdCarrito());
        dto.setEstado(carrito.getEstado());
        return dto;
    }
}