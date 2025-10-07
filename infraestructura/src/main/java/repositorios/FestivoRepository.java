package com.ferney.api.infraestructura.repositorios;

import com.ferney.api.dominio.Festivo;
import com.ferney.api.dominio.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FestivoRepository extends JpaRepository<Festivo, Long> {
    List<Festivo> findByPais(Pais pais);
}
