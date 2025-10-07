package com.ferney.api.aplicacion.controladores;

import com.ferney.api.dominio.Festivo;
import com.ferney.api.aplicacion.servicios.FestivoAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/festivos")
public class FestivoController {

    private final FestivoAppService service;

    public FestivoController(FestivoAppService service) { this.service = service; }

    @GetMapping
    public List<Festivo> all() { return service.all(); }

    @GetMapping("/{id}")
    public ResponseEntity<Festivo> get(@PathVariable Long id) {
        return service.byId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Festivo> create(@RequestBody Festivo f) {
        // Al crear, se asume que el JSON trae tipo.id y pais.id si corresponde
        Festivo saved = service.save(f);
        return ResponseEntity.created(URI.create("/api/festivos/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Festivo> update(@PathVariable Long id, @RequestBody Festivo f) {
        return service.byId(id).map(existing -> {
            existing.setNombre(f.getNombre());
            existing.setDia(f.getDia());
            existing.setMes(f.getMes());
            existing.setDiasPascua(f.getDiasPascua());
            existing.setPais(f.getPais());
            existing.setTipo(f.getTipo());
            return ResponseEntity.ok(service.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.byId(id).map(existing -> { service.delete(id); return ResponseEntity.noContent().build(); })
                .orElse(ResponseEntity.notFound().build());
    }
}
