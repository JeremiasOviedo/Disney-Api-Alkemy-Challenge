package com.jeremias.disneyappalkemy.controller;

import com.jeremias.disneyappalkemy.domain.Pelicula;
import com.jeremias.disneyappalkemy.service.GeneroService;
import com.jeremias.disneyappalkemy.service.PeliculaService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/movies")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;
    
    
    @Autowired
    private GeneroService generoService;
    
    
    @GetMapping()
    public List<Pelicula> peliculas(Model model, @Param("name") String name) {

        return peliculaService.listarPeliculas();

    }

    
    @GetMapping(params="name")
    public List<Pelicula> filtrarPorNombre(@RequestParam("name") String name){
        
        return peliculaService.filtrarPorNombre(name);
        
        
    }
    
    @GetMapping(params="order")
    public List<Pelicula> filtrarPorFecha(@RequestParam("order")String order){
        
        return peliculaService.filtrarPorFecha(order);
        
        
    }
    
     @GetMapping(value = "", params="genre")
    public List<Pelicula> filtrarPorGenero(@RequestParam("genre") Long idGenero){
        return generoService.encontrarPeliculaPorGenero(idGenero);
    }
    
    @DeleteMapping(path = "delete/{id}" )
    
    public String eliminarPelicula (@RequestParam("id") Long id){
        
        try {
            peliculaService.eliminar(id);
            return "pelicula borrada con exito: " + id;
        } catch(Exception e){
            return "no se pudo borrar pelicula: " + id;
        }
        
    }
    
    
    @PostMapping("save")
    public Pelicula agregarPelicula(@RequestParam(name = "file") MultipartFile imagen, @ModelAttribute Pelicula pelicula) {

        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources/static/images");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                pelicula.setImagen(imagen.getOriginalFilename());
            } catch (IOException ex) {
                Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
       return peliculaService.guardar(pelicula);

    }
   
    @PostMapping("{idPelicula}/characters/{idPersonaje}")

    public String agregarPersonajeAPelicula(@RequestParam(name = "idPelicula") Long idPelicula,
            @RequestParam(name = "idPersonaje") Long idPersonaje) {

        peliculaService.agregarPersonaje(idPelicula, idPersonaje);

        return "Personaje añadido con exito: " + idPersonaje;
    }

    @DeleteMapping("{idPelicula}/characters/{idPersonaje}")

    public String eliminarPersonajeDePelicula(@RequestParam(name = "idPelicula") Long idPelicula,
            @RequestParam(name = "idPersonaje") Long idPersonaje) {

        peliculaService.eliminarPersonaje(idPelicula, idPersonaje);

        return "Personaje borrado con exito: " + idPersonaje;
    }

}
