package com.jeremias.disneyappalkemy.controller;

import com.jeremias.disneyappalkemy.domain.Genero;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/genres")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping()
    public List<Genero> listarGeneros() {

        return generoService.listarGeneros();

    }

    @PostMapping("save")
    public Genero agregarGenero(@RequestParam(name = "file") MultipartFile imagen, @ModelAttribute Genero genero) {

        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources/static/images");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                genero.setImagen(imagen.getOriginalFilename());
            } catch (IOException ex) {
                Logger.getLogger(GeneroController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return generoService.guardar(genero);

    }

    @DeleteMapping(path = "delete/{id}")
    public String eliminarGenero(@RequestParam(name = "id") Long idGenero) {
        try {
            generoService.eliminar(idGenero);
            return "Genero borrado con exito: " + idGenero;
        } catch (Exception e) {

            return "No se ha podido eliminar el genero" + idGenero;
        }

    }

     @PostMapping("{idGenero}/movies/{idPelicula}")

    public String agregarPeliculaAGenero(@RequestParam(name = "idGenero") Long idGenero,
            @RequestParam(name = "idPelicula") Long idPelicula) {

        generoService.agregarPelicula(idGenero, idPelicula);

        return "Pelicula añadida con exito: " + idPelicula;
    }

    @DeleteMapping("{idGenero}/movies/{idPelicula}")

    public String eliminarPeliculaDeGenero(@RequestParam(name = "idGenero") Long idGenero,
            @RequestParam(name = "idPelicula") Long idPelicula) {

        generoService.eliminarPelicula(idGenero, idPelicula);

        return "Pelicula borrada con exito: " + idPelicula;
    }

}
