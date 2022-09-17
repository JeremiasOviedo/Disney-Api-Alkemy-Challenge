package com.jeremias.disneyappalkemy.service;

import com.jeremias.disneyappalkemy.domain.Role;
import com.jeremias.disneyappalkemy.domain.User;
import com.jeremias.disneyappalkemy.repository.RoleRepository;
import com.jeremias.disneyappalkemy.repository.UserRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;



//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByNombre(username);
//
//        if (user == null) {
//
//            LOGGER.error("No se encontro al usuario");
//
//        } else {
//
//            LOGGER.info("Se encontro al usuario");
//
//        }
//
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getNombre()));
//        });
//        return new org.springframework.security.core.userdetails.User(user.getNombre(), user.getPassword(), authorities);
//
//    }

    @Override
    public User guardar(User user) {

        return userRepo.save(user);
    }

    @Override
    public boolean eliminar(Long idUser) {

        try {
            userRepo.deleteById(idUser);
            return true;
        } catch (Exception err) {

            return false;
        }
    }

    @Override
    public Role guardarRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void asignarRol(Long idUser, Long idRole) {

        User user = this.getUser(idUser);

        Role role = this.getRole(idRole);

        user.asignarRol(role);

        this.guardar(user);

    }

    @Override
    public void eliminarRol(Long idUser, Long idRole) {

        User user = this.getUser(idUser);

        Role role = this.getRole(idRole);

        user.eliminarRol(role);

        this.guardar(user);

    }

    @Override
    public User getUser(Long idUser) {

        return userRepo.findById(idUser).get();
    }

    @Override
    public Role getRole(Long idRole) {

        return roleRepo.findById(idRole).get();
    }

    @Override
    public List<User> listarUsuarios() {

        return (List<User>) userRepo.findAll();
    }

}
