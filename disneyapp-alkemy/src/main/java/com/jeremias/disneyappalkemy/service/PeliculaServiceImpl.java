package com.jeremias.disneyappalkemy.service;

import com.jeremias.disneyappalkemy.domain.Pelicula;
import com.jeremias.disneyappalkemy.domain.Personaje;
import com.jeremias.disneyappalkemy.repository.PeliculaRepository;
import com.jeremias.disneyappalkemy.repository.PersonajeRepository;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepo;

    @Autowired
    private PersonajeService personajeService;

    
    @Override
    public List<Pelicula> listarPeliculas(){
        
        return (List<Pelicula>) peliculaRepo.findAll();
    }
    @Override
    public List<Pelicula>filtrarPorNombre(String name) {

        if (name != null) {
            return (List<Pelicula>) peliculaRepo.findAll(name);
        }
        return (List<Pelicula>) peliculaRepo.findAll();
    }

    @Override
    public List<Pelicula> filtrarPorFecha(String orden){
         if(orden.equals("ASC")){
            return peliculaRepo.findAllByOrderByFechaDeCreacionAsc();
        }else if(orden.equals("DESC")){
            return peliculaRepo.findAllByOrderByFechaDeCreacionDesc();  
        }else{
            return peliculaRepo.findAll();
        }
    }
    
    

    @Override
    public Pelicula guardar(Pelicula pelicula) {

        return peliculaRepo.save(pelicula);
    }

    @Override
    public boolean eliminar(Long idPelicula) {

        try {
        peliculaRepo.deleteById(idPelicula);
        return true;
                } catch(Exception err){
                    
                    return false;
                }
    }

    @Override
    public Pelicula get(Long idPelicula) {

        return peliculaRepo.findById(idPelicula).get();
    }

    @Override
    public void agregarPersonaje(Long idPelicula, Long idPersonaje) {

        Pelicula pelicula = this.get(idPelicula);

        Personaje personaje = personajeService.get(idPersonaje);

        pelicula.agregarPersonaje(personaje);

        this.guardar(pelicula);

    }

    @Override
    public void eliminarPersonaje(Long idPelicula, Long idPersonaje) {

        Pelicula pelicula = this.get(idPelicula);

        Personaje personaje = personajeService.get(idPersonaje);

        pelicula.eliminarPersonaje(personaje);

        this.guardar(pelicula);

    }

    @Override
    public Set<Personaje> encontrarPersonajePorPelicula(Long idPelicula) {
Pelicula pelicula = peliculaRepo.getById(idPelicula);
        
        if(pelicula!= null){
            Set<Personaje> personajes = pelicula.getPersonajes();
            
            return personajes;
        }else {
            return null;   
        
        }

}
}
