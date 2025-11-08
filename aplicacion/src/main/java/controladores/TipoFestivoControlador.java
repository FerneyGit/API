package controladores;

import entidades.TipoFestivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import servicios.ITipoFestivoServicio;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipos-festivo")
public class TipoFestivoControlador {
    
    @Autowired
    private ITipoFestivoServicio tipoFestivoServicio;
    
    @GetMapping
    public List<TipoFestivo> obtenerTodos() {
        return tipoFestivoServicio.obtenerTodos();
    }
    
    @GetMapping("/{id}")
    public Optional<TipoFestivo> obtenerPorId(@PathVariable Long id) {
        return tipoFestivoServicio.obtenerPorId(id);
    }
    
    @PostMapping
    public TipoFestivo crear(@RequestBody TipoFestivo tipoFestivo) {
        return tipoFestivoServicio.guardar(tipoFestivo);
    }
    
    @PutMapping("/{id}")
    public TipoFestivo actualizar(@PathVariable Long id, @RequestBody TipoFestivo tipoFestivo) {
        tipoFestivo.setId(id);
        return tipoFestivoServicio.guardar(tipoFestivo);
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        tipoFestivoServicio.eliminar(id);
    }
}