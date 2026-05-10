package com.microservice.fastfooddelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.fastfooddelivery.DTO.CatalogoDTO;
import com.microservice.fastfooddelivery.DTO.PromocionDTO;
import com.microservice.fastfooddelivery.model.Catalogo;
import com.microservice.fastfooddelivery.model.Promocion;
import com.microservice.fastfooddelivery.services.PromocionService;

@RestController
@RequestMapping("/api/v1/promocion")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @GetMapping
    public ResponseEntity<List<PromocionDTO>> todasLasPromocion(){
        List<PromocionDTO> promocions = promocionService.obtenerTodos();
        if(promocions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(promocions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromocionDTO> buscarPorId(@PathVariable Integer id) {
        try {
            PromocionDTO promo = promocionService.buscarPorId(id);
            return new ResponseEntity<>(promo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Promocion> agregarPromocion(@RequestBody Promocion promocion) {
        try {
            Promocion guardado = promocionService.guardarPromocion(promocion);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Promocion> editarPromocion(@PathVariable Integer id, @RequestBody Promocion promocion) {
        try {
            Promocion editado = promocionService.guardarPromocion(promocion);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

     @PutMapping("/{id}")
    public ResponseEntity<Promocion> actualizarPromocion(@PathVariable Integer id, @RequestBody Promocion promocion){
        try{
            Catalogo newCatalogo = catalogoService.actualizarCatalogo(id, catalogo);
            return new ResponseEntity<>(newCatalogo, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
