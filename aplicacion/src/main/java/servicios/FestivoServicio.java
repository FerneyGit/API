package servicios;

import dtos.FestivoDto;
import dtos.VerificarFechaResponse;
import entidades.Festivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorios.IFestivoRepositorio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FestivoServicio implements IFestivoServicio {
    
    @Autowired
    private IFestivoRepositorio festivoRepositorio;
    
    @Override
    public List<Festivo> obtenerTodos() {
        return festivoRepositorio.findAll();
    }
    
    @Override
    public Optional<Festivo> obtenerPorId(Long id) {
        return festivoRepositorio.findById(id);
    }
    
    @Override
    public Festivo guardar(Festivo festivo) {
        return festivoRepositorio.save(festivo);
    }
    
    @Override
    public void eliminar(Long id) {
        festivoRepositorio.deleteById(id);
    }
    
    @Override
    public List<FestivoDto> obtenerFestivosPorPisYAnio(Long idPais, int anio) {
        List<Festivo> festivos = festivoRepositorio.obtenerFestivosPorPais(idPais);
        List<FestivoDto> respuesta = new ArrayList<>();
        
        for (Festivo festivo : festivos) {
            LocalDate fecha = calcularFechaFestivo(anio, festivo);
            respuesta.add(new FestivoDto(festivo.getNombre(), fecha));
        }
        
        return respuesta;
    }
    
    @Override
    public VerificarFechaResponse verificarFecha(Long idPais, int anio, int mes, int dia) {
        // Validar si la fecha es válida
        if (!esFechaValida(anio, mes, dia)) {
            return new VerificarFechaResponse("Fecha No valida");
        }
        
        List<Festivo> festivos = festivoRepositorio.obtenerFestivosPorPais(idPais);
        LocalDate fechaConsulta = LocalDate.of(anio, mes, dia);
        
        for (Festivo festivo : festivos) {
            LocalDate fechaFestivo = calcularFechaFestivo(anio, festivo);
            if (fechaFestivo.equals(fechaConsulta)) {
                return new VerificarFechaResponse("Es festivo");
            }
        }
        
        return new VerificarFechaResponse("No es festivo");
    }
    
    private LocalDate calcularFechaFestivo(int anio, Festivo festivo) {
        // Lógica para calcular la fecha del festivo según su tipo
        // Implementar según la fórmula de Pascua y los tipos
        LocalDate pascua = calcularPascua(anio);
        
        switch (festivo.getTipo().getId().intValue()) {
            case 1: // Fijo
                return LocalDate.of(anio, festivo.getMes(), festivo.getDia());
            case 2: // Ley Puente Festivo
                LocalDate fechaBase = LocalDate.of(anio, festivo.getMes(), festivo.getDia());
                return ajustarPuenteFestivo(fechaBase);
            case 3: // Basado en Pascua
                return pascua.plusDays(festivo.getDiasPascua());
            case 4: // Basado en Pascua y Ley Puente Festivo
                LocalDate fechaPascua = pascua.plusDays(festivo.getDiasPascua());
                return ajustarPuenteFestivo(fechaPascua);
            case 5: // Ley Puente Festivo Viernes
                LocalDate fechaBase5 = LocalDate.of(anio, festivo.getMes(), festivo.getDia());
                return ajustarPuenteFestivoViernes(fechaBase5);
            default:
                return null;
        }
    }
    
    private LocalDate calcularPascua(int anio) {
        // Implementación de la fórmula de Pascua
        int a = anio % 19;
        int b = anio % 4;
        int c = anio % 7;
        int d = (19 * a + 24) % 30;
        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;
        
        LocalDate domingoRamos = LocalDate.of(anio, 3, 15).plusDays(dias);
        return domingoRamos.plusDays(7); // Domingo de Pascua
    }
    
    private LocalDate ajustarPuenteFestivo(LocalDate fecha) {
        // Si es domingo, se mueve al lunes
        if (fecha.getDayOfWeek().getValue() == 7) { // Domingo
            return fecha.plusDays(1);
        }
        return fecha;
    }
    
    private LocalDate ajustarPuenteFestivoViernes(LocalDate fecha) {
        // Si es viernes, sábado o domingo, se mueve al lunes
        int diaSemana = fecha.getDayOfWeek().getValue();
        if (diaSemana == 5 || diaSemana == 6 || diaSemana == 7) {
            return fecha.plusDays(8 - diaSemana); // Lunes siguiente
        }
        return fecha;
    }
    
    private boolean esFechaValida(int anio, int mes, int dia) {
        try {
            LocalDate.of(anio, mes, dia);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}