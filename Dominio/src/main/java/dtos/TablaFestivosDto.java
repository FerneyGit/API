package dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TablaFestivosDto {

    @Id

    private String dia;
    private String mes;
    private String nombre;
    private String dpascua;
    private int tipo;
    public TablaFestivosDto(String dia, String mes, String nombre, String dpascua, int tipo) {
        this.dia = dia;
        this.mes = mes;
        this.nombre = nombre;
        this.dpascua = dpascua;
        this.tipo = tipo;
    }
    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }
    public String getMes() {
        return mes;
    }
    public void setMes(String mes) {
        this.mes = mes;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDpascua() {
        return dpascua;
    }
    public void setDpascua(String dpascua) {
        this.dpascua = dpascua;
    }
    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    
}