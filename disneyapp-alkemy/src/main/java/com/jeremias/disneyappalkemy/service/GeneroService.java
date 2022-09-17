package com.jeremias.disneyappalkemy.service;

import com.jeremias.disneyappalkemy.domain.Genero;
import com.jeremias.disneyappalkemy.domain.Pelicula;
import java.util.List;

public interface GeneroService {

    public List<Genero> listarGeneros();

    public Genero guardar(Genero genero);

    public boolean eliminar(Long idGenero);

    public Genero get(Long idGenero);

    public List<Pelicula> encontrarPeliculaPorGenero(Long idGenero);

    public void agregarPelicula(Long idGenero, Long idPelicula);

    public void eliminarPelicula(Long idGenero, Long idPelicula);

}
