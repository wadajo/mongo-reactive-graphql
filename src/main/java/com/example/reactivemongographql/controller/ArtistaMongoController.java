package com.example.reactivemongographql.controller;

import com.example.reactivemongographql.document.Artista;
import com.example.reactivemongographql.repository.ReactiveArtistaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class ArtistaMongoController {

    Logger log= LoggerFactory.getLogger("MyLogger");
    ReactiveArtistaRepository repository;

    public ArtistaMongoController(ReactiveArtistaRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public Flux<Artista> artistas(){
        return repository.findAll();
    }

    @MutationMapping
    public Mono<Artista> addArtista(@Argument ArtistaInput nuevo){
        Artista aAgregar=new Artista(null,nuevo.apellido(),nuevo.estilo());
        Mono<Artista> aDevolver=repository.save(aAgregar);
        return aDevolver;
    }

    @MutationMapping
    public String limpiarBase(){
        repository.deleteAll();
        return "Eliminado todo";
    }

    @MutationMapping
    public String borrar(@Argument String idArtistaABorrar){
        Mono<Artista> aBorrarArtista=repository.findById(idArtistaABorrar);
        repository.deleteById(aBorrarArtista.map(Artista::getId));
        return "Eliminada entrada de ID: "+idArtistaABorrar;
    }

    @MutationMapping
    @Transactional
    public String borrarVarios(@Argument List<String> ids){
        for (String unId:ids) {
            Mono<Artista> aBorrarArtista=repository.findById(unId);
            repository.deleteById(aBorrarArtista.map(Artista::getId));
            log.info("Eliminada entrada de ID: "+unId);
        }
        return "Eliminado todo";
    }
}

record ArtistaInput(String apellido, String estilo){}