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
            catalogoRepository.delete(catalogo);
            return "El catalogo '" + catalogo.getNombre_catalogo() + "' ha sido eliminado correctamente.";
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }

    public Catalogo actualizarCatalogo(Integer id,Catalogo catalogo){
        Catalogo catalog = catalogoRepository.findById(id).orElseThrow(() -> new RuntimeException("¡El héroe no existe en los registros!"));
        if(heroe.getNivel() != null){
            hero.setNivel(heroe.getNivel());
        }
        if(heroe.getNombre() != null){
            hero.setNombre(heroe.getNombre());
        }
        if(heroe.getClase() != null){
            hero.setClase(heroe.getClase());
        }
        return heroeRepository.save(hero);
    }
}
