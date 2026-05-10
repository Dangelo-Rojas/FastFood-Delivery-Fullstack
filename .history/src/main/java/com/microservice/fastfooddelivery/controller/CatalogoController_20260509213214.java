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
import com.microservice.fastfooddelivery.model.Catalogo;
import com.microservice.fastfooddelivery.services.CatalogoService;

@RestController
@RequestMapping("/api/v1/catalogo")
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping
    public ResponseEntity<List<CatalogoDTO>> todosLosCatalogos(){
        List<CatalogoDTO> catalogos = catalogoService.obtenerTodos();
        if(catalogos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(catalogos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            CatalogoDTO catalog = catalogoService.buscarPorId(id);
            return new ResponseEntity<>(catalog, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Catalogo> agregarCatalogo(@RequestBody Catalogo catalogo) {
        try {
            Catalogo guardado = catalogoService.guardarCatalogo(catalogo);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Catalogo> editarCatalogo(@PathVariable Integer id, @RequestBody Catalogo catalogo) {
        try {
            Catalogo editado = catalogoService.guardarCatalogo(catalogo);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Catalogo> actualizarCatalogo(@PathVariable Integer id, @RequestBody Catalogo catalogo){
        try{
            Catalogo newCatalogo = catalogoService.actualizarCatalogo(id, catalogo)
            return new ResponseEntity<>(newHero, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
