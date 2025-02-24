package com.example.rutasmongoredis.model.dto;

import com.example.rutasmongoredis.model.Incidente;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class TramoRuta implements Serializable {
    private List<Interseccion> intersecciones;
    private List<Incidente> incidentes;
}
