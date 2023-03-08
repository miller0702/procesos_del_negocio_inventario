package com.procesos.inventario.services;

import com.procesos.inventario.models.User;
import com.procesos.inventario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Optional<User> getUser(Long id){

        return userRepository.findById(id);
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