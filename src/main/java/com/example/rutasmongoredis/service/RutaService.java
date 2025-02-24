package com.example.rutasmongoredis.service;

import com.example.rutasmongoredis.Repository.RutaRepository;
import com.example.rutasmongoredis.model.Incidente;
import com.example.rutasmongoredis.model.dto.TramoReporte;
import com.example.rutasmongoredis.model.dto.TramoRuta;
import org.bson.types.ObjectId;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RutaService {

    private final RutaRepository rutaRepository;
    private final RedissonClient redisson;
    private final CacheService cacheService;
    public RutaService(RutaRepository rutaRepository, RedissonClient redisson, CacheService cacheService) {
        this.rutaRepository = rutaRepository;
        this.redisson = redisson;
        this.cacheService = cacheService;
    }
    @Caching(evict = {
            @CacheEvict(value = "reporte", key= "#idRuta"),
            @CacheEvict(value = "tramo", allEntries = true)
    })
    public void crearIncidente(Incidente incidente, Long idRuta) {
        incidente.setFechaHora(new Date());
        incidente.setIdIncidente(System.currentTimeMillis());
        rutaRepository.agregarIncidente(incidente,idRuta);
    }
    @Caching(evict = {
            @CacheEvict(value = "reporte", key= "#idRuta"),
            @CacheEvict(value = "tramo", allEntries = true)
    })
    public void eliminarIncidente(Long rutaId, Long idIncidente) {
        rutaRepository.eliminarIncidente(  rutaId,idIncidente);
    }


    public List<TramoReporte> reporte(Long idRuta) {
        return rutaRepository.obtenerReporteRuta(idRuta);
    }

    public List<TramoReporte> reporteRuta(Long idRuta) {
        if (cache(idRuta)) {
            return reporte(idRuta);
        }
        return cacheService.reporte(idRuta);
    }

    public TramoRuta consulta(Long idRuta, Long km) {
        return rutaRepository.consultaIncidente(idRuta, km);
    }

    public TramoRuta consultaIncidente(Long idRuta, Long km){
        if (cache(idRuta)) {
            return consulta(idRuta, km);
        }
        return cacheService.consulta(idRuta, km);
    }


    public boolean cache(Long idRuta){
        String cacheKey = "ruta:" + idRuta;
        RAtomicLong contador = redisson.getAtomicLong("contador:" + cacheKey);
        if (contador.get() == 0) {
            contador.expire(Duration.ofHours(1));
        }

        long invocationCount = contador.incrementAndGet();
        return invocationCount < 5;
    }
}
