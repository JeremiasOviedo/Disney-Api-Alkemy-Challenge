package com.jeremias.disneyappalkemy.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "pelicula")
public class Pelicula implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPelicula;

    @Column
    private String titulo;
    @Column
    private Date fechaDeCreacion;

    @Column
    private float calificacion;

    @Column
    private String imagen;

    
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER,
            cascade
            = {CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
            })
    @JoinTable(
            name = "pelicula_personaje",
            joinColumns = @JoinColumn(name = "id_pelicula"),
            inverseJoinColumns = @JoinColumn(name = "id_personaje")
    )
    @EqualsAndHashCode.Exclude
    public Set<Personaje> personajes;

    public void agregarPersonaje(Personaje personaje) {
        personajes.add(personaje);

    }

    public void eliminarPersonaje(Personaje personaje) {
        personajes.remove(personaje);

    }

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
            name = "genero_pelicula",
            joinColumns = @JoinColumn(name = "id_pelicula"),
            inverseJoinColumns = @JoinColumn(name = "id_genero")
    )
    private Set<Genero> generos;

}
