package com.jeremias.disneyappalkemy.repository;

import com.jeremias.disneyappalkemy.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role, Long>{
    
    Role findByNombre(String nombre);
    
}
