package com.jeremias.disneyappalkemy.controller;

import com.jeremias.disneyappalkemy.domain.Personaje;
import com.jeremias.disneyappalkemy.service.PeliculaService;
import com.jeremias.disneyappalkemy.service.PersonajeService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/characters")
@RestController
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;
    
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping()
    public List<Personaje> listarPersonajes() {

        return personajeService.listarPersonajes();

    }

    @GetMapping(params = "name")
    public List<Personaje> filtrarPorNombre(@RequestParam("name") String name) {

        return personajeService.filtrarPorNombre(name);

    }

    @GetMapping(params = "age")
    public List<Personaje> filtrarPorEdad(@RequestParam("age") int age) {

        return personajeService.filtrarPorEdad(age);
    }
    
     @GetMapping(value = "", params="movie")
    public Set<Personaje> filtrarPorPelicula(@RequestParam("movie") Long idPelicula){
        
        return peliculaService.encontrarPersonajePorPelicula(idPelicula);
    }

    @PostMapping("save")
    public Personaje guardarPersonaje(@RequestParam(name = "file") MultipartFile imagen, @ModelAttribute Personaje personaje) {

        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources/static/images");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                personaje.setImagen(imagen.getOriginalFilename());
            } catch (IOException ex) {
                Logger.getLogger(PersonajeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return personajeService.guardar(personaje);

    }
    
    @DeleteMapping("/delete/{id}")
    public String borrarPersonaje(@RequestParam("id") Long id) {

        try {
            personajeService.eliminar(id);
            return "Personaje borrado con exito :" + id;
        } catch (Exception e) {

            return "No ha sido posible eliminar el personaje";
        }

    }


}
