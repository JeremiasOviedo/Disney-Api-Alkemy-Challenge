package com.jeremias.disneyappalkemy.service;

import com.jeremias.disneyappalkemy.domain.Genero;
import com.jeremias.disneyappalkemy.domain.Pelicula;
import com.jeremias.disneyappalkemy.domain.Personaje;
import com.jeremias.disneyappalkemy.repository.GeneroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GeneroServiceImpl implements GeneroService{
    
    @Autowired
    private GeneroRepository generoRepo;
    
    @Autowired
    private PeliculaService peliculaService;

    @Override
    public List<Genero> listarGeneros() {
        
        return (List<Genero>)generoRepo.findAll();
    }

    @Override
    public Genero guardar(Genero genero) {
        
        return generoRepo.save(genero);
    }

    @Override
    public boolean eliminar(Long idGenero) {
        try{
        generoRepo.deleteById(idGenero);
        
        return true;
        } catch(Exception err){
            
            return false;
        }
    }

    @Override
    public Genero get(Long idGenero) {
        return generoRepo.findById(idGenero).get();
    }

    @Override
    public List<Pelicula> encontrarPeliculaPorGenero(Long idGenero) {
        Genero genero = generoRepo.getById(idGenero);
        
        if(genero != null){
            List<Pelicula> peliculas = genero.getPeliculas();
            
            return peliculas;
        }else {
            return null;
        }
    }
    
     @Override
    public void agregarPelicula(Long idGenero, Long idPelicula) {

        Genero genero = this.get(idGenero);

        Pelicula pelicula = peliculaService.get(idPelicula);

        genero.agregarPelicula(pelicula);

        this.guardar(genero);

    }

    @Override
    public void eliminarPelicula(Long idGenero, Long idPelicula) {

        Genero genero = this.get(idGenero);

        Pelicula pelicula = peliculaService.get(idPelicula);

        genero.eliminarPelicula(pelicula);

        this.guardar(genero);

    }

}
