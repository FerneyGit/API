package repositorios;

import entidades.Festivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IFestivoRepositorio extends JpaRepository<Festivo, Long> {
    List<Festivo> findByPaisId(Long paisId);
    
    @Query("SELECT f FROM Festivo f WHERE f.pais.id = :paisId")
    List<Festivo> obtenerFestivosPorPais(@Param("paisId") Long paisId);
}