package com.jeremias.disneyappalkemy.repository;

import com.jeremias.disneyappalkemy.domain.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    public Pelicula getById(Long idPelicula);
    
    @Query("SELECT p FROM Pelicula p WHERE p.titulo LIKE %?1%")
    List<Pelicula> findAll(String name);

    List<Pelicula> findAllByOrderByFechaDeCreacionAsc();

    List<Pelicula> findAllByOrderByFechaDeCreacionDesc();
    
//    List<Pelicula> findAllByIdGenero(Long idGenero);

}
