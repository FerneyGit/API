package servicios;

import entidades.TipoFestivo;
import java.util.List;
import java.util.Optional;

public interface ITipoFestivoServicio {
    List<TipoFestivo> obtenerTodos();
    Optional<TipoFestivo> obtenerPorId(Long id);
    TipoFestivo guardar(TipoFestivo tipoFestivo);
    void eliminar(Long id);
}