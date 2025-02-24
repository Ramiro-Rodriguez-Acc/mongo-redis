package com.example.rutasmongoredis.model;

import com.example.rutasmongoredis.model.dto.Interseccion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
    private List<Interseccion> interesecciones;
    private List<Incidente> incidentes;
}
