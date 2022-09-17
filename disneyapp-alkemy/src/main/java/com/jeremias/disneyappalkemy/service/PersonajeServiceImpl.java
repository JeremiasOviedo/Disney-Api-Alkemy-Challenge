package com.jeremias.disneyappalkemy.service;

import com.jeremias.disneyappalkemy.domain.Personaje;
import com.jeremias.disneyappalkemy.repository.PersonajeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepo;

    @Override
    public List<Personaje> listarPersonajes() {

        return (List<Personaje>) personajeRepo.findAll();
    }

    @Override
    public List<Personaje> filtrarPorNombre(String name) {

        if (name != null) {
            return (List<Personaje>) personajeRepo.findAll(name);
        }

        return (List<Personaje>) personajeRepo.findAll();

    }

    @Override
    public List<Personaje> filtrarPorPelicula(Long idMovie) {

        return (List<Personaje>) personajeRepo.findByPeliculas(idMovie);
    }

    @Override
    public List<Personaje> filtrarPorEdad(int age) {

        return (List<Personaje>) personajeRepo.findByEdad(age);
    }

    @Override
    public Personaje guardar(Personaje personaje) {

        return personajeRepo.save(personaje);
    }

    @Override
    public boolean eliminar(Long idPersonaje) {
        try {
            personajeRepo.deleteById(idPersonaje);

            return true;
        } catch (Exception err) {
            return false;
        }
    }
       
    @Override
        public Personaje get(Long idPersonaje ) {

        return personajeRepo.findById(idPersonaje).get();
        }

    }
