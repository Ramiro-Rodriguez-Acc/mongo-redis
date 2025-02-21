package com.example.rutasmongoredis.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoIncidente {

    FOTOMULTA(1),
    CONTROL_POLICIAL(2),
    ACCIDENTE(3),
    BACHE(1),
    INCENDIO(3),
    NEBLINA(1),
    ANIMALES(2),
    PIQUETE(2);

    private final int importancia;

    TipoIncidente(int importancia) {
        this.importancia = importancia;
    }


    public int getImportancia() {
        return importancia;
    }

    @JsonValue
    public String toJson() {
        return this.name();
    }
}
