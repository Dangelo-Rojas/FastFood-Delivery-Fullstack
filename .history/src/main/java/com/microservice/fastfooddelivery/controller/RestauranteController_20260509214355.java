package com.microservice.fastfooddelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.fastfooddelivery.services.RestauranteService;

@RestController
@RequestMapping("/api/v1/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

}
