package com.ferney.api.aplicacion.servicios;

import com.ferney.api.core.servicios.TipoFestivoService;
import com.ferney.api.dominio.TipoFestivo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TipoFestivoAppService {
    private final TipoFestivoService service;
    public TipoFestivoAppService(TipoFestivoService service) { this.service = service; }

    public List<TipoFestivo> all() { return service.findAll(); }
    public Optional<TipoFestivo> byId(Long id) { return service.findById(id); }
    public TipoFestivo save(TipoFestivo t) { return service.save(t); }
    public void delete(Long id) { service.deleteById(id); }
}
