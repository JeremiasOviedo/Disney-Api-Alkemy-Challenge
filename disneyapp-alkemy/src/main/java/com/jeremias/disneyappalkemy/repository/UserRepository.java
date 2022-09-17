package com.jeremias.disneyappalkemy.repository;

import com.jeremias.disneyappalkemy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{
    
    User findByNombre(String nombre);
    
    
}
