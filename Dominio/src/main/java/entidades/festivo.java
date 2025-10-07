package com.ferney.api.dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "festivo")
public class festivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Para festivos fijos
    private Integer dia;
    private Integer mes;

    // Offset en días respecto al Domingo de Pascua (null si no aplica)
    private Integer diasPascua;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private TipoFestivo tipo;

    @ManyToOne
    @JoinColumn(name = "pais_id")
    private com.ferney.api.dominio.pais pais;

    public festivo() {}

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getDia() { return dia; }
    public void setDia(Integer dia) { this.dia = dia; }
    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }
    public Integer getDiasPascua() { return diasPascua; }
    public void setDiasPascua(Integer diasPascua) { this.diasPascua = diasPascua; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public TipoFestivo getTipo() { return tipo; }
    public void setTipo(TipoFestivo tipo) { this.tipo = tipo; }
    public Pais getPais() { return pais; }
    public void setPais(Pais pais) { this.pais = pais; }
}
