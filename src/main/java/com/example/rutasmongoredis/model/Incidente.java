package com.example.rutasmongoredis.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Incidente implements Serializable {

    private Long idIncidente;
    private Integer km;
    private TipoIncidente tipoIncidente;
    private Date fechaHora;
    private String comentarios;


}
