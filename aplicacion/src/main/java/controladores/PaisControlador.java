package controladores;

import entidades.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import servicios.IPaisServicio;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paises")
public class PaisControlador {
    
    @Autowired
    private IPaisServicio paisServicio;
    
    @GetMapping
    public List<Pais> obtenerTodos() {
        return paisServicio.obtenerTodos();
    }
    
    @GetMapping("/{id}")
    public Optional<Pais> obtenerPorId(@PathVariable Long id) {
        return paisServicio.obtenerPorId(id);
    }
    
    @PostMapping
    public Pais crear(@RequestBody Pais pais) {
        return paisServicio.guardar(pais);
    }
    
    @PutMapping("/{id}")
    public Pais actualizar(@PathVariable Long id, @RequestBody Pais pais) {
        pais.setId(id);
        return paisServicio.guardar(pais);
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        paisServicio.eliminar(id);
    }
}