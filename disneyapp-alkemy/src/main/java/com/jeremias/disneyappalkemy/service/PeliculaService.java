package com.jeremias.disneyappalkemy.service;

import com.jeremias.disneyappalkemy.domain.Pelicula;
import com.jeremias.disneyappalkemy.domain.Personaje;
import java.util.List;
import java.util.Set;

public interface PeliculaService {
    
    
    public List<Pelicula> listarPeliculas();
    
    public List<Pelicula> filtrarPorNombre(String name);
   
    public List<Pelicula> filtrarPorFecha(String orden);
    
    public Pelicula guardar(Pelicula pelicula);
    
    public boolean eliminar (Long idPelicula);
    
    public Pelicula get (Long idPelicula);
    
    public void agregarPersonaje(Long idPelicula, Long idPersonaje);
    
    public void eliminarPersonaje(Long idPelicula, Long idPersonaje);
    
    public Set<Personaje> encontrarPersonajePorPelicula(Long idPelicula);
}
