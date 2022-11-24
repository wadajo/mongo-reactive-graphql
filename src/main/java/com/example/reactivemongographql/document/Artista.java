package com.example.reactivemongographql.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document
public class Artista {

    @Id
    private String id;
    private String apellido;
    private String estilo;

    public Artista(String id, String apellido, String estilo) {
        this.id = id;
        this.apellido = apellido;
        this.estilo = estilo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
}
