package entidades;

import javax.persistence.*;

@Entity
@Table(name = "festivo")
public class Festivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "idpais", nullable = false)
    private Pais pais;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "dia")
    private Integer dia;
    
    @Column(name = "mes")
    private Integer mes;
    
    @Column(name = "diaspascua")
    private Integer diasPascua;
    
    @ManyToOne
    @JoinColumn(name = "idtipo", nullable = false)
    private TipoFestivo tipo;
    
    // Constructores
    public Festivo() {}
    
    public Festivo(Long id, Pais pais, String nombre, Integer dia, Integer mes, Integer diasPascua, TipoFestivo tipo) {
        this.id = id;
        this.pais = pais;
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.diasPascua = diasPascua;
        this.tipo = tipo;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Pais getPais() { return pais; }
    public void setPais(Pais pais) { this.pais = pais; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public Integer getDia() { return dia; }
    public void setDia(Integer dia) { this.dia = dia; }
    
    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }
    
    public Integer getDiasPascua() { return diasPascua; }
    public void setDiasPascua(Integer diasPascua) { this.diasPascua = diasPascua; }
    
    public TipoFestivo getTipo() { return tipo; }
    public void setTipo(TipoFestivo tipo) { this.tipo = tipo; }
}