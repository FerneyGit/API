package com.ferney.api.aplicacion.controladores;

import com.ferney.api.dominio.Pais;
import com.ferney.api.aplicacion.servicios.PaisAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/paises")
public class PaisController {
    private final PaisAppService service;
    public PaisController(PaisAppService service) { this.service = service; }

    @GetMapping
    public List<Pais> all() { return service.all(); }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> get(@PathVariable Long id) {
        return service.byId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pais> create(@RequestBody Pais p) {
        Pais saved = service.save(p);
        return ResponseEntity.created(URI.create("/api/paises/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pais> update(@PathVariable Long id, @RequestBody Pais p) {
        return service.byId(id).map(existing -> {
            existing.setNombre(p.getNombre());
            return ResponseEntity.ok(service.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.byId(id).map(existing -> {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
