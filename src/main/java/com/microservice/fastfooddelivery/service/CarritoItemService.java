package com.microservice.fastfooddelivery.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.CarritoItemDTO;
import com.microservice.fastfooddelivery.model.Carrito;
import com.microservice.fastfooddelivery.model.CarritoItem;
import com.microservice.fastfooddelivery.repository.CarritoItemRepository;
import com.microservice.fastfooddelivery.repository.CarritoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarritoItemService {

    private static final Logger log = LoggerFactory.getLogger(CarritoItemService.class);

    @Autowired
    private CarritoItemRepository carritoItemRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    public List<CarritoItemDTO> obtenerTodos() {
        log.info("Obteniendo todos los items del carrito");
        return carritoItemRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<CarritoItemDTO> obtenerPorCarrito(Integer idCarrito) {
        log.info("Obteniendo items del carrito con ID: {}", idCarrito);
        return carritoItemRepository.findByCarritoIdCarrito(idCarrito).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public CarritoItemDTO buscarPorId(Integer id) {
        log.info("Buscando item con ID: {}", id);
        CarritoItem item = carritoItemRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Item no encontrado con ID: {}", id);
                    return new RuntimeException("El item con ID " + id + " no existe.");
                });
        return convertirADTO(item);
    }

    public CarritoItemDTO guardar(CarritoItem item) {
        log.info("Guardando nuevo item en carrito ID: {}", item.getCarrito().getIdCarrito());
        Carrito carrito = carritoRepository.findById(item.getCarrito().getIdCarrito())
                .orElseThrow(() -> {
                    log.error("Carrito no encontrado con ID: {}", item.getCarrito().getIdCarrito());
                    return new RuntimeException("El carrito con ID " + item.getCarrito().getIdCarrito() + " no existe.");
                });
        item.setCarrito(carrito);
        CarritoItemDTO guardado = convertirADTO(carritoItemRepository.save(item));
        log.info("Item guardado con ID: {}", guardado.getIdCarritoItem());
        return guardado;
    }

    public CarritoItemDTO actualizar(Integer id, CarritoItem item) {
        log.info("Actualizando item con ID: {}", id);
        CarritoItem itemExistente = carritoItemRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Item no encontrado con ID: {}", id);
                    return new RuntimeException("El item con ID " + id + " no existe.");
                });
        if (item.getCantidad() != null) {
            itemExistente.setCantidad(item.getCantidad());
        }
        if (item.getPrecioUnitario() != null) {
            itemExistente.setPrecioUnitario(item.getPrecioUnitario());
        }
        log.info("Item con ID: {} actualizado exitosamente", id);
        return convertirADTO(carritoItemRepository.save(itemExistente));
    }

    public String eliminar(Integer id) {
        log.info("Eliminando item con ID: {}", id);
        CarritoItem item = carritoItemRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Item no encontrado con ID: {}", id);
                    return new RuntimeException("El item con ID " + id + " no existe.");
                });
        carritoItemRepository.delete(item);
        log.info("Item con ID: {} eliminado exitosamente", id);
        return "El item con ID " + id + " ha sido eliminado correctamente.";
    }

    private CarritoItemDTO convertirADTO(CarritoItem item) {
        CarritoItemDTO dto = new CarritoItemDTO();
        dto.setIdCarritoItem(item.getIdCarritoItem());
        dto.setCantidad(item.getCantidad());
        dto.setPrecioUnitario(item.getPrecioUnitario());
        dto.setIdCarrito(item.getCarrito().getIdCarrito());
        return dto;
    }
}