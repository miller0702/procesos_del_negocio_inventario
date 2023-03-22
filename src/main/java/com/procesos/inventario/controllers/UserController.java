package com.procesos.inventario.controllers;

import com.procesos.inventario.models.User;
import com.procesos.inventario.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity findUserById(@PathVariable Long id){
        Map response = new HashMap();
        try {
            return new ResponseEntity(userServiceImp.getUser(id), HttpStatus.OK);
        }catch (Exception e){
            response.put("status","404");
            response.put("message","No se encontro el usuario!");
            return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/user")
    public ResponseEntity  saveUser(@RequestBody User user){
        Map response = new HashMap();
        Boolean userResp = userServiceImp.createUser(user);

        if (userResp == true){
            response.put("status","201");
            response.put("message","Se guardo el usuario!");
            return  new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        response.put("status","400");
        response.put("message","No se guardo el usuario!");
        return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/users")
    public ResponseEntity  Users(){
        Map response = new HashMap();
        try {
            return new ResponseEntity(userServiceImp.allUsers(), HttpStatus.OK);
        }catch (Exception e){
            response.put("status","404");
            response.put("message","No hay usuarios!");
            return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity  updateUser(@PathVariable Long id,@RequestBody User user){
        Boolean resp = userServiceImp.updateUser(id,user);
        Map response = new HashMap();
        if (resp == true){
            return new ResponseEntity(userServiceImp.updateUser(id,user), HttpStatus.OK);
        }else{
            response.put("status","400");
            response.put("message","No se actualizo el usuario!");
            return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
}

