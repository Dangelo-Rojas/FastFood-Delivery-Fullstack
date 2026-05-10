package com.microservice.fastfooddelivery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.model.Catalogo;
import com.microservice.fastfooddelivery.repository.CatalogoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CatalogoService {

    @Autowired
    private CatalogoRepository catalogoRepository;

    public Catalogo guardarCatalogo(Catalogo catalogo){
        return catalogoRepository.save(catalogo);
    }

    public String eliminar(Integer id) {
        try {
            Catalogo catalogo = catalogoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("El catalogo con ID" + id + " no existe."));
        }
    }
}
