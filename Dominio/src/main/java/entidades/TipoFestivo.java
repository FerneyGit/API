package com.ferney.api.dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_festivo")
public class TipoFestivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // modo: 1,2,3,4 según enunciado
    @Column(nullable = false)
    private Integer modo;

    @Column(nullable = false)
    private String nombre;

    public TipoFestivo() {}
    public TipoFestivo(Integer modo, String nombre) { this.modo = modo; this.nombre = nombre; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getModo() { return modo; }
    public void setModo(Integer modo) { this.modo = modo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
