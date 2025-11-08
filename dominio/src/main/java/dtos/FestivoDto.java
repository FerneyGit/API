package dtos;

import java.time.LocalDate;

public class FestivoDto {
    private String festivo;
    private LocalDate fecha;
    
    public FestivoDto() {}
    
    public FestivoDto(String festivo, LocalDate fecha) {
        this.festivo = festivo;
        this.fecha = fecha;
    }
    
    // Getters y Setters
    public String getFestivo() { return festivo; }
    public void setFestivo(String festivo) { this.festivo = festivo; }
    
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}