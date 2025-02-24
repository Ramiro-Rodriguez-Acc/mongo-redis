package com.example.rutasmongoredis.controller;

import com.example.rutasmongoredis.model.Incidente;
import com.example.rutasmongoredis.model.dto.TramoReporte;
import com.example.rutasmongoredis.model.dto.TramoRuta;
import com.example.rutasmongoredis.service.RutaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/rutas")
@RestController()
public class RutaController {
    private final RutaService rutaService;
    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }
    @PostMapping("/{idRuta}")
    public String crearIncidente(@RequestBody Incidente incidente, @PathVariable Long idRuta) {
        rutaService.crearIncidente(incidente, idRuta);
        return "Incidente creada";
    }
    @DeleteMapping("/{idRuta}/eliminar/{idIncidente}")
    public String eliminarIncidente(@PathVariable Long idIncidente, @PathVariable Long idRuta) {
        rutaService.eliminarIncidente( idRuta,idIncidente);
        return "Incidente eliminado";
    }

    @GetMapping("/{idRuta}")
    public List<TramoReporte> reporte(@PathVariable Long idRuta){
        return rutaService.reporteRuta(idRuta);
    }

    @GetMapping("/{idRuta}/consulta/{km}")
    public  TramoRuta  consultaIncidente(@PathVariable Long idRuta, @PathVariable Long km){
        return rutaService.consultaIncidente(idRuta,km);
    }



}
