package com.microservice.fastfooddelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.fastfooddelivery.DTO.PagoDTO;
import com.microservice.fastfooddelivery.model.Pago;
import com.microservice.fastfooddelivery.service.PagoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> obtenerTodos() {
        return new ResponseEntity<>(pagoService.obtenerTodos(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(pagoService.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping("/orden/{idOrden}")
    public ResponseEntity<List<PagoDTO>> obtenerPorOrden(@PathVariable Long idOrden) {
        return new ResponseEntity<>(pagoService.obtenerPorOrden(idOrden), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PagoDTO>> obtenerPorEstado(@PathVariable String estado) {
        return new ResponseEntity<>(pagoService.obtenerPorEstado(estado), HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<PagoDTO> guardar(@Valid @RequestBody Pago pago) {
        return new ResponseEntity<>(pagoService.guardar(pago), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Object> cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        return new ResponseEntity<>(pagoService.cambiarEstado(id, estado), HttpStatus.OK);
    }
        
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return new ResponseEntity<>(pagoService.eliminar(id), HttpStatus.OK);
    }
}
