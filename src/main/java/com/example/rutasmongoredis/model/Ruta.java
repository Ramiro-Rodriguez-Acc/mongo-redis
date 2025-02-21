package com.example.rutasmongoredis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "rutas")
@Getter
@Setter
public class Ruta {
    @Id
    private Long id;
    private String nombre;
    private String origen;
    private String destino;
    private Long distancia;
    @JsonIgnoreProperties("interesecciones")
    private List<Ruta> interesecciones;
    private List<Incidente> incidentes;
}
