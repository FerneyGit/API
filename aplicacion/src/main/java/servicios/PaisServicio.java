package servicios;

import entidades.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorios.IPaisRepositorio;
import java.util.List;
import java.util.Optional;

@Service
public class PaisServicio implements IPaisServicio {
    
    @Autowired
    private IPaisRepositorio paisRepositorio;
    
    @Override
    public List<Pais> obtenerTodos() {
        return paisRepositorio.findAll();
    }
    
    @Override
    public Optional<Pais> obtenerPorId(Long id) {
        return paisRepositorio.findById(id);
    }
    
    @Override
    public Pais guardar(Pais pais) {
        return paisRepositorio.save(pais);
    }
    
    @Override
    public void eliminar(Long id) {
        paisRepositorio.deleteById(id);
    }
}