package servicios;

import entidades.TipoFestivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorios.ITipoFestivoRepositorio;
import java.util.List;
import java.util.Optional;

@Service
public class TipoFestivoServicio implements ITipoFestivoServicio {
    
    @Autowired
    private ITipoFestivoRepositorio tipoFestivoRepositorio;
    
    @Override
    public List<TipoFestivo> obtenerTodos() {
        return tipoFestivoRepositorio.findAll();
    }
    
    @Override
    public Optional<TipoFestivo> obtenerPorId(Long id) {
        return tipoFestivoRepositorio.findById(id);
    }
    
    @Override
    public TipoFestivo guardar(TipoFestivo tipoFestivo) {
        return tipoFestivoRepositorio.save(tipoFestivo);
    }
    
    @Override
    public void eliminar(Long id) {
        tipoFestivoRepositorio.deleteById(id);
    }
}