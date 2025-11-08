package servicios;

import entidades.Pais;
import java.util.List;
import java.util.Optional;

public interface IPaisServicio {
    List<Pais> obtenerTodos();
    Optional<Pais> obtenerPorId(Long id);
    Pais guardar(Pais pais);
    void eliminar(Long id);
}