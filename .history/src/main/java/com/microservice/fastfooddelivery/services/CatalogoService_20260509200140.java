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
        Catalogo catalog = catalogoRepository.findById(id).orElseThrow(() -> new RuntimeException("El catalogo no existe en nuestros registros."));
        if(catalogo.getCategoria() != null){
            catalog.setCategoria(catalogo.getCategoria());
        }
        if(catalogo.getNombre_catalogo() != null){
            catalog.setNombre_catalogo(catalogo.getNombre_catalogo());
        }
        if(catalogo.getDescripcion_catalogo() != null){
            catalog.setDescripcion_catalogo(catalogo.getDescripcion_catalogo());;
        }
        return catalogoRepository.save(catalog);
    }

    public CatalogoDTO buscarPorId(Integer id) {
        Catalogo catalogo = catalogoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Héroe no encontrado!"));
        return convertirADTO(heroe);
    }


}
