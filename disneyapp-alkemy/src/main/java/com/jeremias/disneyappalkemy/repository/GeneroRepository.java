package com.jeremias.disneyappalkemy.repository;

import com.jeremias.disneyappalkemy.domain.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    public Genero getById(Long idGenero);
}
