package com.procesos.inventario.services;

import com.procesos.inventario.models.User;
import com.procesos.inventario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;
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
}
   /*User user = new User();
        user.setId(1L);
        user.setFirstName("Miller");
        user.setLastName("Alvarez");
        user.setAddress("KDX 1 A");
        user.setEmail("mdalvarezb@ufpso.edu.co");
        user.setPassword("123456");
        user.setBirthday(new Date(101,10,02));*/


//userBD.setPassword(user.getPassword());
//userBD.setEmail(user.getEmail());