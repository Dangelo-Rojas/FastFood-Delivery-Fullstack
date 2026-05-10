package com.microservice.fastfooddelivery.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.fastfooddelivery.DTO.CatalogoDTO;
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
            .orElseThrow(() -> new RuntimeException("Catalogo no encontrado"));
        return convertirADTO(catalogo);
    }

    private CatalogoDTO convertirADTO(Catalogo catalogo) {
        CatalogoDTO dto = new CatalogoDTO();
        dto.setId(heroe.getId());
        dto.setNombre(heroe.getNombre());
        dto.setClase(heroe.getClase());
        dto.setNivel(heroe.getNivel());

        if (heroe.getParty() != null) {
            dto.setNombreParty(heroe.getParty().getNombre());
        }else{
            dto.setNombreParty("Lobo solitario, auuu");
        }

        if (heroe.getArma() != null) {
            dto.setNombreArma(heroe.getArma().getNombre());
        } else {
            dto.setNombreArma("Desarmado, todos contra mi solo");
        }

        List<String> nombresHechizos = new ArrayList<>();
        if(heroe.getHechizosAprendidos() != null) {
            for(LibroHechizos nexo : heroe.getHechizosAprendidos()) {
                nombresHechizos.add(nexo.getMagia().getNombre());
            }
        }
        dto.setHechizos(nombresHechizos);
        return dto;
    }


}
