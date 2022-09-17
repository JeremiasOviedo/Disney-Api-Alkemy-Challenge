package com.jeremias.disneyappalkemy.service;

import com.jeremias.disneyappalkemy.domain.Personaje;
import java.util.List;

public interface PersonajeService {
    
    public List<Personaje> listarPersonajes();
  
    public List <Personaje> filtrarPorNombre(String name);
    
    public List<Personaje> filtrarPorPelicula(Long idPelicula);
    
    public List<Personaje> filtrarPorEdad(int edad);
    
    
    
    public Personaje guardar(Personaje personaje);
    
    public boolean eliminar(Long idPersonaje);
    
    public Personaje get(Long idPersonaje);
    
}
