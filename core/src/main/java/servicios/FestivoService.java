package com.ferney.api.core.servicios;

import com.ferney.api.dominio.Festivo;
import com.ferney.api.dominio.Pais;
import com.ferney.api.dominio.TipoFestivo;
import com.ferney.api.infraestructura.repositorios.FestivoRepository;
import com.ferney.api.infraestructura.repositorios.PaisRepository;
import com.ferney.api.infraestructura.repositorios.TipoFestivoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FestivoService {
    private final FestivoRepository festivoRepo;
    private final PaisRepository paisRepo;
    private final TipoFestivoRepository tipoRepo;

    public FestivoService(FestivoRepository festivoRepo, PaisRepository paisRepo, TipoFestivoRepository tipoRepo) {
        this.festivoRepo = festivoRepo;
        this.paisRepo = paisRepo;
        this.tipoRepo = tipoRepo;
    }

    // CRUD basico
    public List<Festivo> findAll() { return festivoRepo.findAll(); }
    public Optional<Festivo> findById(Long id) { return festivoRepo.findById(id); }
    public Festivo save(Festivo f) { return festivoRepo.save(f); }
    public void deleteById(Long id) { festivoRepo.deleteById(id); }
    public List<Festivo> findByPais(Pais p) { return festivoRepo.findByPais(p); }

    // ---- Lógica: cálculo Domingo de Pascua (según PDF) ----
    public LocalDate easterSunday(int year) {
        int a = year % 19;
        int b = year % 4;
        int c = year % 7;
        int d = (19 * a + 24) % 30;
        int dias = d + ((2 * b + 4 * c + 6 * d + 5) % 7);
        // Domingo de Ramos = 15 de marzo + dias; Domingo de Pascua = Domingo de Ramos + 7
        LocalDate domingoRamos = LocalDate.of(year, Month.MARCH, 15).plusDays(dias);
        return domingoRamos.plusDays(7);
    }

    // Calcula la fecha observada de un festivo para un año (aplica los modos 1..4)
    public LocalDate calculateObservedDate(Festivo f, int year) {
        if (f == null || f.getTipo() == null) return null;
        TipoFestivo tipo = f.getTipo();
        int modo = tipo.getModo();

        if (modo == 1) { // fijo (no varía)
            if (f.getDia() == null || f.getMes() == null)
                throw new IllegalArgumentException("Festivo fijo sin dia/mes");
            return LocalDate.of(year, f.getMes(), f.getDia());
        } else if (modo == 2) { // ley de puente: se traslada al siguiente lunes
            if (f.getDia() == null || f.getMes() == null)
                throw new IllegalArgumentException("Festivo tipo 2 sin dia/mes");
            LocalDate d = LocalDate.of(year, f.getMes(), f.getDia());
            return d.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        } else if (modo == 3) { // basado en Pascua
            if (f.getDiasPascua() == null)
                throw new IllegalArgumentException("Festivo tipo 3 sin diasPascua");
            LocalDate easter = easterSunday(year);
            return easter.plusDays(f.getDiasPascua());
        } else if (modo == 4) { // Pascua + ley de puente
            if (f.getDiasPascua() == null)
                throw new IllegalArgumentException("Festivo tipo 4 sin diasPascua");
            LocalDate easter = easterSunday(year);
            LocalDate d = easter.plusDays(f.getDiasPascua());
            return d.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        } else {
            throw new IllegalArgumentException("Modo desconocido: " + modo);
        }
    }

    // lista los festivos observados de un pais en un año (ordenados)
    public List<Map<String,Object>> listHolidays(Long paisId, int year) {
        Pais pais = paisRepo.findById(paisId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pais no encontrado: " + paisId));
        List<Festivo> festivos = festivoRepo.findByPais(pais);
        List<Map<String,Object>> result = new ArrayList<>();

        for (Festivo f : festivos) {
            try {
                LocalDate observed = calculateObservedDate(f, year);
                Map<String,Object> m = new HashMap<>();
                m.put("festivoId", f.getId());
                m.put("nombre", f.getNombre());
                m.put("tipoModo", f.getTipo().getModo());
                m.put("fechaObservada", observed);
                if (f.getDia() != null && f.getMes() != null)
                    m.put("fechaOrigen", LocalDate.of(year, f.getMes(), f.getDia()));
                else
                    m.put("fechaOrigen", null);
                result.add(m);
            } catch (Exception ex) {
                // omitir festivos mal configurados
            }
        }

        return result.stream()
                .sorted(Comparator.comparing(m -> (LocalDate) m.get("fechaObservada")))
                .collect(Collectors.toList());
    }

    // valida si una fecha dada es festiva en el pais (devuelve lista de coincidencias)
    public List<Map<String,Object>> isHoliday(Long paisId, LocalDate fecha) {
        Pais pais = paisRepo.findById(paisId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pais no encontrado: " + paisId));
        int year = fecha.getYear();
        List<Festivo> festivos = festivoRepo.findByPais(pais);
        List<Map<String,Object>> matches = new ArrayList<>();
        for (Festivo f : festivos) {
            try {
                LocalDate observed = calculateObservedDate(f, year);
                if (observed != null && observed.equals(fecha)) {
                    Map<String,Object> m = new HashMap<>();
                    m.put("festivoId", f.getId());
                    m.put("nombre", f.getNombre());
                    m.put("fechaObservada", observed);
                    m.put("tipoModo", f.getTipo().getModo());
                    matches.add(m);
                }
            } catch (Exception ex) {
                // ignore
            }
        }
        return matches;
    }
}
