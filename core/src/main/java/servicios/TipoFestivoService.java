package com.ferney.api.core.servicios;

import com.ferney.api.dominio.TipoFestivo;
import com.ferney.api.infraestructura.repositorios.TipoFestivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoFestivoService {
    private final TipoFestivoRepository repo;
    public TipoFestivoService(TipoFestivoRepository repo) { this.repo = repo; }

    public List<TipoFestivo> findAll() { return repo.findAll(); }
    public Optional<TipoFestivo> findById(Long id) { return repo.findById(id); }
    public TipoFestivo save(TipoFestivo t) { return repo.save(t); }
    public void deleteById(Long id) { repo.deleteById(id); }
}
