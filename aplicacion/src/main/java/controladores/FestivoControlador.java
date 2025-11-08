package controladores;

import dtos.FestivoDto;
import dtos.VerificarFechaResponse;
import entidades.Festivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import servicios.IFestivoServicio;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {
    
    @Autowired
    private IFestivoServicio festivoServicio;
    
    @GetMapping
    public List<Festivo> obtenerTodos() {
        return festivoServicio.obtenerTodos();
    }
    
    @GetMapping("/{id}")
    public Optional<Festivo> obtenerPorId(@PathVariable Long id) {
        return festivoServicio.obtenerPorId(id);
    }
    
    @PostMapping
    public Festivo crear(@RequestBody Festivo festivo) {
        return festivoServicio.guardar(festivo);
    }
    
    @PutMapping("/{id}")
    public Festivo actualizar(@PathVariable Long id, @RequestBody Festivo festivo) {
        festivo.setId(id);
        return festivoServicio.guardar(festivo);
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        festivoServicio.eliminar(id);
    }
    
    @GetMapping("/festivos/{idPais}/{anio}")
    public List<FestivoDto> obtenerFestivos(@PathVariable Long idPais, @PathVariable int anio) {
        return festivoServicio.obtenerFestivosPorPisYAnio(idPais, anio);
    }
    
    @GetMapping("/verificar/{idPais}/{anio}/{mes}/{dia}")
    public VerificarFechaResponse verificarFecha(
            @PathVariable Long idPais,
            @PathVariable int anio,
            @PathVariable int mes,
            @PathVariable int dia) {
        return festivoServicio.verificarFecha(idPais, anio, mes, dia);
    }
}