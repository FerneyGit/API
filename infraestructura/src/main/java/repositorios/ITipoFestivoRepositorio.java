package repositorios;

import entidades.TipoFestivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoFestivoRepositorio extends JpaRepository<TipoFestivo, Long> {
}