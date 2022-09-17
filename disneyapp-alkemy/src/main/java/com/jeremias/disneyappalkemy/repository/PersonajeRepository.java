package com.jeremias.disneyappalkemy.repository;

import com.jeremias.disneyappalkemy.domain.Personaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonajeRepository extends JpaRepository <Personaje, Long> {
    
  
    @Query("SELECT p FROM Personaje p WHERE p.nombre LIKE %?1%")
   public List<Personaje> findAll(String name);
    
   
   
    public abstract List<Personaje> findByPeliculas(Long idMovie);
    
    
  public abstract List<Personaje> findByEdad(int age);
    
}
