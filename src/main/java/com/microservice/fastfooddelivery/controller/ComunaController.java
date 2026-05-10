package com.microservice.fastfooddelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.fastfooddelivery.DTO.ComunaDTO;
import com.microservice.fastfooddelivery.model.Comuna;
import com.microservice.fastfooddelivery.service.ComunaService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<ComunaDTO>> obtenerTodas() {
        List<ComunaDTO> comunas = comunaService.obtenerTodas();
        if (comunas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comunas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunaDTO> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(comunaService.buscarPorId(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ComunaDTO> crear(@Valid @RequestBody Comuna comuna) {
    try {
        ComunaDTO dto = comunaService.guardar(comuna);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

    @PutMapping("/{id}")
    public ResponseEntity<ComunaDTO> actualizar(@PathVariable Long id, @RequestBody Comuna comuna) {
        try { 
            Comuna actualizada = comunaService.actualizar(id, comuna);
            return new ResponseEntity<>(comunaService.buscarPorId(actualizada.getIdComuna()), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(comunaService.eliminar(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
