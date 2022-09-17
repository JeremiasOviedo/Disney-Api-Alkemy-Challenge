package com.jeremias.disneyappalkemy.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "personaje")
public class Personaje implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersonaje;

    @Column(length = 45, unique = true)
    private String nombre;

    @Column
    private int edad;

    @Column
    private float peso;

    @Column(length = 255)
    private String historia;

    @Column
    private String imagen;

@JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade
            = {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
            })
    @JoinTable(
            name = "pelicula_personaje",
            joinColumns = @JoinColumn(name = "id_personaje"),
            inverseJoinColumns = @JoinColumn(name = "id_pelicula"))
    @EqualsAndHashCode.Exclude
    public Set<Pelicula> peliculas;

}
