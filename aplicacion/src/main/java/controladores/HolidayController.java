package com.ferney.api.aplicacion.controladores;

import com.ferney.api.aplicacion.servicios.FestivoAppService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/paises")
public class HolidayController {

    private final FestivoAppService festivoApp;

    public HolidayController(FestivoAppService festivoApp) {
        this.festivoApp = festivoApp;
    }

    // GET /api/paises/{paisId}/esFestivo?date=yyyy-MM-dd
    @GetMapping("/{paisId}/esFestivo")
    public ResponseEntity<?> esFestivo(
            @PathVariable Long paisId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        if (date == null) {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Formato de fecha inválido");
        }
        List<Map<String,Object>> matches = festivoApp.isHoliday(paisId, date);
        if (matches == null || matches.isEmpty()) {
            return ResponseEntity.ok(Map.of("fecha", date, "esFestivo", false));
        } else {
            return ResponseEntity.ok(Map.of("fecha", date, "esFestivo", true, "festivos", matches));
        }
    }

    // GET /api/paises/{paisId}/festivos/{year}
    @GetMapping("/{paisId}/festivos/{year}")
    public ResponseEntity<?> listaFestivos(@PathVariable Long paisId, @PathVariable int year) {
        List<Map<String,Object>> list = festivoApp.listHolidays(paisId, year);
        return ResponseEntity.ok(list);
    }
}
