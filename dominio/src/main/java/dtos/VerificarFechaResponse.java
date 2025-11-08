package dtos;

public class VerificarFechaResponse {
    private String mensaje;
    
    public VerificarFechaResponse() {}
    
    public VerificarFechaResponse(String mensaje) {
        this.mensaje = mensaje;
    }
    
    // Getters y Setters
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}