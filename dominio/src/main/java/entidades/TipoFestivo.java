package entidades;

import javax.persistence.*;

@Entity
@Table(name = "tipofestivo")
public class TipoFestivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tipo", unique = true, nullable = false)
    private String tipo;
    
    // Constructores
    public TipoFestivo() {}
    
    public TipoFestivo(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}