package com.ferney.api.core.servicios;

import com.ferney.api.dominio.Pais;
import com.ferney.api.infraestructura.repositorios.PaisRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService {
    private final PaisRepository repo;
    public PaisService(PaisRepository repo) { this.repo = repo; }

    public List<Pais> findAll() { return repo.findAll(); }
    public Optional<Pais> findById(Long id) { return repo.findById(id); }
    public Pais save(Pais p) { return repo.save(p); }
    public void deleteById(Long id) { repo.deleteById(id); }
}
