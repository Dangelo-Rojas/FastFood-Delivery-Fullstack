package com.microservice.fastfooddelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.fastfooddelivery.DTO.PromocionDTO;
import com.microservice.fastfooddelivery.model.Restaurante;
import com.microservice.fastfooddelivery.services.RestauranteService;

@RestController
@RequestMapping("/api/v1/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> todosLosRestaurantes(){
        List<RestauranteDTO> restaurant = restauranteService.obtenerTodos();
        if(restaurant.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(promocions, HttpStatus.OK);
    }

}
