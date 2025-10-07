package com.ferney.api.aplicacion.servicios;

import com.ferney.api.core.servicios.FestivoService;
import com.ferney.api.dominio.Festivo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class FestivoAppService {
    private final FestivoService service;
    public FestivoAppService(FestivoService service) { this.service = service; }

    public List<Festivo> all() { return service.findAll(); }
    public Optional<Festivo> byId(Long id) { return service.findById(id); }
    public Festivo save(Festivo f) { return service.save(f); }
    public void delete(Long id) { service.deleteById(id); }

    // delegaciones
    public List<Map<String,Object>> listHolidays(Long paisId, int year) { return service.listHolidays(paisId, year); }
    public List<Map<String,Object>> isHoliday(Long paisId, LocalDate date) { return service.isHoliday(paisId, date); }
}
