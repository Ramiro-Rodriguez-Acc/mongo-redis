package com.example.rutasmongoredis.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Interseccion implements Serializable {
    private String nombre;
    private Long km;
}
