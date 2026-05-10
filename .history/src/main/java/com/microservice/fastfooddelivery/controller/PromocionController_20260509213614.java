package com.microservice.fastfooddelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.fastfooddelivery.DTO.CatalogoDTO;
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
        if(catalogos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(catalogos, HttpStatus.OK);
    }
}
