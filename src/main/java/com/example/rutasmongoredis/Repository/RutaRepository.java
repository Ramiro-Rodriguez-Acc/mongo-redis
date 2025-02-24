package com.example.rutasmongoredis.Repository;

import com.example.rutasmongoredis.model.Incidente;
import com.example.rutasmongoredis.model.Ruta;
import com.example.rutasmongoredis.model.dto.Interseccion;
import com.example.rutasmongoredis.model.dto.TramoReporte;
import com.example.rutasmongoredis.model.dto.TramoRuta;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class RutaRepository {

    private final MongoTemplate mongoTemplate;

    RutaRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void agregarIncidente(Incidente incidente, Long idRuta) {
        Query query = new Query(where("_id").is(idRuta));
        Update update = new Update().push("incidentes", incidente);
         mongoTemplate.updateFirst(query, update, "rutas");
    }


    public void eliminarIncidente( Long idRuta, Long idIncidente) {
        Query query = new Query(where("_id").is(idRuta));
        Update update = new Update().pull("incidentes", Query.query(where("idIncidente").is(idIncidente)));
        mongoTemplate.updateFirst(query, update, "rutas");
    }

    public List<TramoReporte> obtenerReporteRuta(Long idRuta) {
        MatchOperation matchRuta = Aggregation.match(Criteria.where("_id").is(idRuta));

        UnwindOperation unwindIncidentes = Aggregation.unwind("incidentes");

        LookupOperation lookupTiposIncidente = Aggregation.lookup(
                "tipos_incidente",
                "incidentes.tipoIncidente",
                "_id",
                "tipo"
        );


        UnwindOperation unwindTipo = Aggregation.unwind("tipo");

        ProjectionOperation calcularGravedad = Aggregation.project()
                .andExpression("floor(incidentes.km / 100) * 100").as("inicioTramo")
                .and("tipo.gravedad").as("gravedad");


        GroupOperation agruparPorTramo = Aggregation.group("inicioTramo")
                .sum("gravedad").as("gravedadTotal");


        SortOperation ordenarPorGravedad = Aggregation.sort(Sort.by(Sort.Direction.DESC, "gravedadTotal"));

        ProjectionOperation proyectar = Aggregation.project()
                .and("_id").as("inicioTramo")
                .and("gravedadTotal").as("gravedadTotal");

        Aggregation aggregation = Aggregation.newAggregation(
                matchRuta, unwindIncidentes, lookupTiposIncidente, unwindTipo, calcularGravedad, agruparPorTramo, ordenarPorGravedad, proyectar
        );

        return mongoTemplate.aggregate(aggregation, "rutas", TramoReporte.class).getMappedResults();
    }


    public TramoRuta consultaIncidente(Long idRuta, Long km) {
        MatchOperation matchRuta = Aggregation.match(Criteria.where("_id").is(idRuta));

        UnwindOperation unwindIncidentes = Aggregation.unwind("incidentes");
        MatchOperation filtrarIncidentes = Aggregation.match(
                Criteria.where("incidentes.km").gte(km).lt(km + 100)
        );

        UnwindOperation unwindIntersecciones = Aggregation.unwind("intersecciones");
        MatchOperation filtrarIntersecciones = Aggregation.match(
                Criteria.where("intersecciones.km").gte(km).lt(km + 100)
        );

        ProjectionOperation proyectarDatosIncidentes = Aggregation.project()
                .and("incidentes.idIncidente").as("idIncidente")
                .and("incidentes.km").as("km")
                .and("incidentes.tipoIncidente").as("tipoIncidente")
                .and("incidentes.comentarios").as("comentarios")
                .and("incidentes.fechaHora").as("fechaHora");

        ProjectionOperation proyectarDatosIntersecciones = Aggregation.project()
                .and("intersecciones.km").as("km")
                .and("intersecciones.nombre").as("nombre");


        Aggregation aggregationIncidentes = Aggregation.newAggregation(
                matchRuta,
                unwindIncidentes,
                filtrarIncidentes,
                proyectarDatosIncidentes
        );

        Aggregation aggregationIntersecciones = Aggregation.newAggregation(
                matchRuta,
                unwindIntersecciones,
                filtrarIntersecciones,
                proyectarDatosIntersecciones
        );
        List<Incidente> incidentes =mongoTemplate.aggregate(aggregationIncidentes, "rutas", Incidente.class).getMappedResults();
        List<Interseccion> intersecciones =mongoTemplate.aggregate(aggregationIntersecciones, "rutas", Interseccion.class).getMappedResults();

        return new TramoRuta(intersecciones,incidentes);
    }
}