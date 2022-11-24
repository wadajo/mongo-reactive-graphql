package com.example.reactivemongographql;

import com.example.reactivemongographql.document.Artista;
import com.example.reactivemongographql.repository.ReactiveArtistaRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReactiveMongoGraphqlApplicationTests {

	@Autowired
	ReactiveArtistaRepository repository;

	@Test
	void givenNewArtista_whenFindById_thenFindArtista() {
		Artista nuevo=repository.save(new Artista(null,"Casile","Videoarte")).block();
		Flux<Artista> artistaFlux = repository.findAllByApellido(nuevo.getApellido());
		System.out.println(artistaFlux.blockFirst().getApellido());

		StepVerifier
				.create(artistaFlux)
				.assertNext(artista -> {
					assertEquals("Casile", artista.getApellido());
					assertNotNull(artista.getId());
				})
				.expectComplete()
				.verify();
	}

	@AfterAll
	void limpiarBase(){
		repository.deleteAll();
	}
}
