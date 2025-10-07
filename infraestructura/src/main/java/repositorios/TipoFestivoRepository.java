package com.ferney.api.infraestructura.repositorios;

import com.ferney.api.dominio.TipoFestivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoFestivoRepository extends JpaRepository<TipoFestivo, Long> {
}
