package com.example.rutasmongoredis.service;

import com.example.rutasmongoredis.Repository.RutaRepository;
import com.example.rutasmongoredis.model.dto.TramoReporte;
import com.example.rutasmongoredis.model.dto.TramoRuta;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CacheService {
    private final RutaRepository rutaRepository;
    public CacheService(RutaRepository rutaRepository) {
        this.rutaRepository = rutaRepository;

    }

    @Cacheable(value = "reporte", key = "#idRuta")
    public List<TramoReporte> reporte(Long idRuta) {
        return rutaRepository.obtenerReporteRuta(idRuta);
    }

    @Cacheable (value = "tramo", key = "#idRuta + ':' + #km")
    public TramoRuta consulta(Long idRuta, Long km) {
        return rutaRepository.consultaIncidente(idRuta, km);
    }
}
