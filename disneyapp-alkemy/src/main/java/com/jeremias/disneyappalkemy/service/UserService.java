package com.jeremias.disneyappalkemy.service;

import com.jeremias.disneyappalkemy.domain.Role;
import com.jeremias.disneyappalkemy.domain.User;
import java.util.List;

public interface UserService {
    
    public User guardar(User user);
    
    public boolean eliminar(Long idUser);
    
    public Role guardarRole (Role role);
    
    public void asignarRol(Long idUser, Long idRole);
    
      public void eliminarRol(Long idUser, Long idRole);
    
    public User getUser(Long idUser);
    
    public Role getRole(Long idRole);
    
    public List<User> listarUsuarios();
    
}
