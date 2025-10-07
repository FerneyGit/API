package com.ferney.api.aplicacion.servicios;

import com.ferney.api.core.servicios.PaisService;
import com.ferney.api.dominio.Pais;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PaisAppService {
    private final PaisService service;
    public PaisAppService(PaisService service) { this.service = service; }
    public List<Pais> all() { return service.findAll(); }
    public Optional<Pais> byId(Long id) { return service.findById(id); }
    public Pais save(Pais p) { return service.save(p); }
    public void delete(Long id) { service.deleteById(id); }
}
