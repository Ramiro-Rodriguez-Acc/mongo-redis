package com.example.rutasmongoredis.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TramoReporte implements Serializable {
    private Long inicioTramo;


    private Long gravedadTotal;
}
