package com.jeremias.disneyappalkemy.controller;

import com.jeremias.disneyappalkemy.domain.Role;
import com.jeremias.disneyappalkemy.domain.User;
import com.jeremias.disneyappalkemy.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;
    

    
    @GetMapping()
    public List<User> listarUsers() {

        return userService.listarUsuarios();
    }

   
    @DeleteMapping(path="delete/id")
    public String eliminarUsuario(@RequestParam("id") Long id) {

        try {
            userService.eliminar(id);
            return "usuario borrado con exito: " + id;
        } catch (Exception e) {
            return "no se pudo borrar el usuario: " + id;
        }

    }

    @PostMapping("/register")
    public User registrarUsuario(@ModelAttribute User user) {

        return userService.guardar(user);

    }
    
    @PostMapping("role/register")
    public Role guardarRole (@ModelAttribute Role role){
        
        return userService.guardarRole(role);
    }
    
     @PostMapping("{idPelicula}/characters/{idPersonaje}")

    public String asignarRolAUser(@RequestParam(name = "idUser") Long idUser,
            @RequestParam(name = "idRole") Long idRole) {

        userService.asignarRol(idUser, idRole);

        return "Personaje añadido con exito: " + idRole;
    }

    @DeleteMapping("{idPelicula}/characters/{idPersonaje}")

    public String eliminarRolDeUser(@RequestParam(name = "idUser") Long idUser,
            @RequestParam(name = "idRole") Long idRole) {

        userService.eliminarRol(idUser, idRole);

        return "Rol eliminado con exito: " + idRole;
    }
}
