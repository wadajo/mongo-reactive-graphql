package com.example.reactivemongographql.repository;

import com.example.reactivemongographql.document.Artista;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveArtistaRepository extends ReactiveMongoRepository<Artista,String> {

    Mono<Artista> findByApellido(String apellido);

    Flux<Artista> findAllByApellido(String apellido);
}
