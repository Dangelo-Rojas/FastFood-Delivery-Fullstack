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
import org.springframework.web.bind.annotation.RestController;

import com.microservice.fastfooddelivery.DTO.DireccionDTO;
import com.microservice.fastfooddelivery.model.Direccion;
import com.microservice.fastfooddelivery.service.DireccionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/direcciones")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @GetMapping
    public ResponseEntity<List<DireccionDTO>> obtenerTodas() {
        List<DireccionDTO> direcciones = direccionService.obtenerTodas();
        if (direcciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(direcciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionDTO> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(direccionService.buscarPorId(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<DireccionDTO>> obtenerPorUsuario(@PathVariable Long idUsuario) {
        List<DireccionDTO> direcciones = direccionService.obtenerPorUsuario(idUsuario);
        if (direcciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(direcciones, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<DireccionDTO> guardar(@Valid @RequestBody Direccion direccion) {
        try {
            DireccionDTO dto = direccionService.guardar(direccion);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Direccion> actualizar(@PathVariable Long id, @RequestBody Direccion direccion) {
        try {
            return new ResponseEntity<>(direccionService.actualizar(id, direccion), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(direccionService.eliminar(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
