package com.jeremias.disneyappalkemy.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "genero")
public class Genero implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGenero;

    @Column(length = 45, nullable = false, unique = true)
    private String nombre;

    @Column()
    private String imagen;

    
    @JsonManagedReference
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
            joinColumns = @JoinColumn(name = "id_genero"),
            inverseJoinColumns = @JoinColumn(name = "id_pelicula")
    )
    public List<Pelicula> peliculas;

    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);

    }

    public void eliminarPelicula(Pelicula pelicula) {
        peliculas.remove(pelicula);

    }

}
