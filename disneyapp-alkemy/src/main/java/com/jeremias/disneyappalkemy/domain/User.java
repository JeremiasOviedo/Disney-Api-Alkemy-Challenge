package com.jeremias.disneyappalkemy.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column
    private String nombre;

    @Column
    private String email;

    @Column
    private String password;

    
     @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER,
            cascade
            = {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
            })
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    @EqualsAndHashCode.Exclude
        private Set<Role> roles = new HashSet<>();

  

     public void asignarRol(Role role) {
       roles.add(role);

    }

    public void eliminarRol(Role role) {
        roles.remove(role);

    }
    
      public User(Long idUser, String nombre, String email, String password) {
        
        this.idUser = idUser;
        this.nombre= nombre;
        this.email = email;
        this.password = password;
        
    }
}
