package com.ferney.api.config;

import com.ferney.api.dominio.Festivo;
import com.ferney.api.dominio.Pais;
import com.ferney.api.dominio.TipoFestivo;
import com.ferney.api.infraestructura.repositorios.FestivoRepository;
import com.ferney.api.infraestructura.repositorios.PaisRepository;
import com.ferney.api.infraestructura.repositorios.TipoFestivoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final PaisRepository paisRepo;
    private final TipoFestivoRepository tipoRepo;
    private final FestivoRepository festivoRepo;

    public DataLoader(PaisRepository paisRepo, TipoFestivoRepository tipoRepo, FestivoRepository festivoRepo) {
        this.paisRepo = paisRepo;
        this.tipoRepo = tipoRepo;
        this.festivoRepo = festivoRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (paisRepo.count() > 0) return; // ya cargado
        Pais colombia = paisRepo.save(new Pais("Colombia"));

        TipoFestivo t1 = tipoRepo.save(new TipoFestivo(1, "Fijo"));
        TipoFestivo t2 = tipoRepo.save(new TipoFestivo(2, "Ley de puente"));
        TipoFestivo t3 = tipoRepo.save(new TipoFestivo(3, "Basado en Pascua"));
        TipoFestivo t4 = tipoRepo.save(new TipoFestivo(4, "Pascua + Puente"));

        // Fijos (modo 1)
        festivoRepo.save(createFestivo(1,1,"Año nuevo",t1,colombia));
        festivoRepo.save(createFestivo(1,5,"Día del Trabajo",t1,colombia));
        festivoRepo.save(createFestivo(20,7,"Independencia Colombia",t1,colombia));
        festivoRepo.save(createFestivo(7,8,"Batalla de Boyacá",t1,colombia));
        festivoRepo.save(createFestivo(8,12,"Inmaculada Concepción",t1,colombia));
        festivoRepo.save(createFestivo(25,12,"Navidad",t1,colombia));

        // Puentes (modo 2)
        festivoRepo.save(createFestivo(6,1,"Santos Reyes",t2,colombia));
        festivoRepo.save(createFestivo(19,3,"San José",t2,colombia));
        festivoRepo.save(createFestivo(29,6,"San Pedro y San Pablo",t2,colombia));
        festivoRepo.save(createFestivo(15,8,"Asunción de la Virgen",t2,colombia));
        festivoRepo.save(createFestivo(12,10,"Día de la Raza",t2,colombia));
        festivoRepo.save(createFestivo(1,11,"Todos los santos",t2,colombia));
        festivoRepo.save(createFestivo(11,11,"Independencia de Cartagena",t2,colombia));

        // Basados en Pascua (modo 3)
        festivoRepo.save(createFestivo(null,null,"Jueves Santo",t3,colombia,-3));
        festivoRepo.save(createFestivo(null,null,"Viernes Santo",t3,colombia,-2));
        festivoRepo.save(createFestivo(null,null,"Domingo de Pascua",t3,colombia,0));

        // Pascua + Puente (modo 4)
        festivoRepo.save(createFestivo(null,null,"Ascensión del Señor",t4,colombia,40));
        festivoRepo.save(createFestivo(null,null,"Corpus Christi",t4,colombia,61));
        festivoRepo.save(createFestivo(null,null,"Sagrado Corazón de Jesús",t4,colombia,68));
    }

    private Festivo createFestivo(Integer dia, Integer mes, String nombre, TipoFestivo tipo, Pais pais) {
        Festivo f = new Festivo();
        f.setDia(dia);
        f.setMes(mes);
        f.setNombre(nombre);
        f.setTipo(tipo);
        f.setPais(pais);
        return f;
    }

    private Festivo createFestivo(Integer dia, Integer mes, String nombre, TipoFestivo tipo, Pais pais, Integer diasPascua) {
        Festivo f = createFestivo(dia, mes, nombre, tipo, pais);
        f.setDiasPascua(diasPascua);
        return f;
    }
}
