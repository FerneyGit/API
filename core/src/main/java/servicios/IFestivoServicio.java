package servicios;

import dtos.FestivoDto;
import dtos.VerificarFechaResponse;
import entidades.Festivo;
import java.util.List;
import java.util.Optional;

public interface IFestivoServicio {
    List<Festivo> obtenerTodos();
    Optional<Festivo> obtenerPorId(Long id);
    Festivo guardar(Festivo festivo);
    void eliminar(Long id);
    
    List<FestivoDto> obtenerFestivosPorPisYAnio(Long idPais, int anio);
    VerificarFechaResponse verificarFecha(Long idPais, int anio, int mes, int dia);
}