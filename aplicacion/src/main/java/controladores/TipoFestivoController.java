package com.ferney.api.aplicacion.controladores;

import com.ferney.api.dominio.TipoFestivo;
import com.ferney.api.aplicacion.servicios.TipoFestivoAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tipos")
public class TipoFestivoController {
    private final TipoFestivoAppService service;
    public TipoFestivoController(TipoFestivoAppService service) { this.service = service; }

    @GetMapping
    public List<TipoFestivo> all() { return service.all(); }

    @GetMapping("/{id}")
    public ResponseEntity<TipoFestivo> get(@PathVariable Long id) {
        return service.byId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoFestivo> create(@RequestBody TipoFestivo t) {
        TipoFestivo saved = service.save(t);
        return ResponseEntity.created(URI.create("/api/tipos/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoFestivo> update(@PathVariable Long id, @RequestBody TipoFestivo t) {
        return service.byId(id).map(existing -> {
            existing.setModo(t.getModo());
            existing.setNombre(t.getNombre());
            return ResponseEntity.ok(service.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.byId(id).map(existing -> { service.delete(id); return ResponseEntity.noContent().build(); })
                .orElse(ResponseEntity.notFound().build());
    }
}
