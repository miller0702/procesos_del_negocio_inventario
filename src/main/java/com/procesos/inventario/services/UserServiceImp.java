package com.procesos.inventario.services;

import com.procesos.inventario.models.User;
import com.procesos.inventario.repository.UserRepository;
import com.procesos.inventario.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtil jwtUtil;
    public User getUser(Long id){

        return userRepository.findById(id).get();
    }

    @Override
    public Boolean createUser(User user) {
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public Boolean updateUser(Long id, User user) {
        try {
            User userBD = userRepository.findById(id).get();
            if (userBD == null) {
                return false;
            }
            userBD.setFirstName(user.getFirstName());
            userBD.setLastName(user.getLastName());
            userBD.setBirthday(user.getBirthday());
            userBD.setAddress(user.getAddress());
            userRepository.save(userBD);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String login(User user){
        Optional<User> userBD = userRepository.findByEmail(user.getEmail());
        if (userBD.isEmpty()){
            throw new RuntimeException("Usuario no encontrado");
        }
        if (!userBD.get().getPassword().equals(user.getPassword())){
            throw new RuntimeException("Contraseña incorrecta");
        }
        return jwtUtil.create(String.valueOf(userBD.get().getId()),String.valueOf(userBD.get().getEmail()));
    }
}